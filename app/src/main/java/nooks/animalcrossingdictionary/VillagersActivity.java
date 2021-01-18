package nooks.animalcrossingdictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.List;

import nooks.animalcrossingdictionary.adapter.AdapterVillager;
import nooks.animalcrossingdictionary.entities.villagers.Villagers;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class VillagersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Switch viewSwitch;
    //private SwitchButton viewSwitch;
    private EditText searchName;
    private RadioGroup nsChooseRadio;
    private Spinner spinner_species, spinner_month;
    private Button searchButton, resetButton;
    private TextView resultNum;

    private String switchSelect = "list";
    private String radioSelect = "All";
    private String speciesSelect = "All species";
    private String monthSelect = "All months";
    private String nameSearch = "";

    private ArrayAdapter<String> spinnerAdapterSpecies;
    //private ArrayAdapter<String> spinnerAdapterMonth;

    private DividerItemDecoration splitLine;

    private List<Villagers> villagers;
    private List<String> speciesList = new ArrayList<>();
    private List<String> monthList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villagers);

        recyclerView = findViewById(R.id.recycleList);
        viewSwitch = findViewById(R.id.viewSwitch);
        searchName = findViewById(R.id.inputName);
        nsChooseRadio = findViewById(R.id.radioGroup);
        spinner_species = findViewById(R.id.speices);
        //spinner_month = findViewById(R.id.month);
        searchButton = findViewById(R.id.searchButton);
        resetButton = findViewById(R.id.reset);
        resultNum = findViewById(R.id.resultNum);

        splitLine = new DividerItemDecoration(VillagersActivity.this, DividerItemDecoration.VERTICAL);

        viewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                    switchSelect = "list";
                else
                    switchSelect = "grid";
                getData();
            }
        });

        nsChooseRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                radioSelect = (String) radioButton.getText();
                getData();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameSearch = searchName.getText().toString();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive() && getCurrentFocus() != null) {
                    if (getCurrentFocus().getWindowToken() != null) {
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                searchName.clearFocus();
                getData();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSelect = "list";
                viewSwitch.setChecked(false);
                radioSelect = "All";
                RadioButton button_all = (RadioButton) nsChooseRadio.findViewById(R.id.all);
                nsChooseRadio.check(button_all.getId());
                speciesSelect = "All species";
                //monthSelect = "All months";
                nameSearch = "";
                getData();
            }
        });

        getData();
    }

    private void getData() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GetRequest.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest get = retrofit.create(GetRequest.class);
        get.getVillagers().enqueue(new Callback<List<Villagers>>() {
            @Override
            public void onResponse(Call<List<Villagers>> call, Response<List<Villagers>> response) {
                List<Villagers> villagersResponse = response.body();
                villagers = villagersResponse;
                villagers = searchGender(villagers);
                villagers = searchVillagerName(villagers);

                speciesList = getSpeciesList(villagers);
                spinnerAdapterSpecies = new ArrayAdapter<>(VillagersActivity.this, android.R.layout.simple_spinner_item, speciesList);
                spinner_species.setAdapter(spinnerAdapterSpecies);
                spinnerAdapterSpecies.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                if (speciesList.indexOf(speciesSelect) != -1) {
                    spinner_species.setSelection(speciesList.indexOf(speciesSelect), true);
                } else {
                    int position_location = spinnerAdapterSpecies.getPosition(speciesSelect);
                    spinner_species.setSelection(position_location);
                }

                spinner_species.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String speciesSelectNew = (String) parent.getItemAtPosition(position);
                        if (!speciesSelect.equals(speciesSelectNew)) {
                            speciesSelect = speciesSelectNew;
                            getData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                villagers = searchSpecies(villagers);

                /*monthList = getMonthList(villagers);
                spinnerAdapterMonth = new ArrayAdapter<>(VillagersActivity.this, android.R.layout.simple_spinner_item, monthList);
                spinner_month.setAdapter(spinnerAdapterMonth);
                spinnerAdapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                if (monthList.indexOf(monthSelect) != -1) {
                    spinner_month.setSelection(monthList.indexOf(monthSelect), true);
                } else {
                    int position_location = spinnerAdapterMonth.getPosition(monthSelect);
                    spinner_month.setSelection(position_location);
                }

                spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String monthSelectNew = (String) parent.getItemAtPosition(position);
                        if (!monthSelect.equals(monthSelectNew)) {
                            monthSelect = monthSelectNew;
                            getData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                villagers = searchMonth(villagers);*/
                AdapterVillager adapterVillager = new AdapterVillager(villagers, switchSelect);
                resultNum.setText(adapterVillager.getItemCount() + " results");
                if (switchSelect.equals("list")) {
                    recyclerView.removeItemDecoration(splitLine);
                    recyclerView.setLayoutManager(new LinearLayoutManager(VillagersActivity.this));
                    recyclerView.addItemDecoration(splitLine);
                    recyclerView.setAdapter(adapterVillager);
                } else if (switchSelect.equals("grid")) {
                    recyclerView.setLayoutManager(new GridLayoutManager(VillagersActivity.this, 3));
                    recyclerView.removeItemDecoration(splitLine);
                    recyclerView.setAdapter(adapterVillager);
                }
            }

            @Override
            public void onFailure(Call<List<Villagers>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

    private List<Villagers> searchGender(List<Villagers> villagers) {
        List<Villagers> result = new ArrayList<>();

        if(radioSelect.equals("All"))
            return villagers;

        for(Villagers villager : villagers) {
            if(villager.getGender().equals(radioSelect))
                result.add(villager);
        }

        return result;
    }

/*    private List<Villagers> searchMonth(List<Villagers> villagers) {
        List<Villagers> result = new ArrayList<>();

        if (monthSelect.equals("All months"))
            return villagers;

        for (Villagers villager : villagers)
            if (monthSelect.equals(villager.getBirthdayString().split(" ")[0]))
                result.add(villager);

        return result;
    }

    private List<String> getMonthList(List<Villagers> villagers) {
        List<String> months = new ArrayList<>();
        months.add("All months");
        for (Villagers villager : villagers)
            if (!months.contains(villager.getBirthdayString().split(" ")[0]))
                months.add(villager.getBirthdayString().split(" ")[0]);
        return months;
    }*/

    private List<Villagers> searchSpecies(List<Villagers> villagers) {
        List<Villagers> result = new ArrayList<>();

        if (speciesSelect.equals("All species"))
            return villagers;

        for (Villagers villager : villagers)
            if (villager.getSpecies().equals(speciesSelect))
                result.add(villager);

        return result;

    }

    private List<String> getSpeciesList(List<Villagers> villagers) {
        List<String> specs = new ArrayList<>();
        specs.add("All species");
        for (Villagers villager : villagers)
            if (!specs.contains(villager.getSpecies())) {
                specs.add(villager.getSpecies());
            }

        return specs;
    }

    private List<Villagers> searchVillagerName(List<Villagers> villagers) {
        List<Villagers> result = new ArrayList<>();

        if (nameSearch.equals(""))
            return villagers;

        for (Villagers villager : villagers)
            if (villager.getName().getNameEUen().contains(nameSearch))
                result.add(villager);

        return result;
    }

    public void fishButton(View view) {
        Intent intent = new Intent(this, FishActivity.class);
        startActivity(intent);
    }

    public void bugsButton(View view) {
        Intent intent = new Intent(this, BugsActivity.class);
        startActivity(intent);
    }

    public void seaCreaturesButton(View view) {
        Intent intent = new Intent(this, SeaCreaturesActivity.class);
        startActivity(intent);
    }

    public void fossilsButton(View view) {
        Intent intent = new Intent(this, FossilsActivity.class);
        startActivity(intent);
    }

    public void songsButton(View view) {
        Intent intent = new Intent(this, SongsActivity.class);
        startActivity(intent);
    }
}