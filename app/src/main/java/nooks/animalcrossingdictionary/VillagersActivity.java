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
    int[] count = {0, 0, 0, 0, 0};

    private RecyclerView recyclerView;
    private Switch viewSwitch;
    private EditText searchName;
    private Spinner spinner_species, spinner_gender;
    private Button searchButton, resetButton;
    private TextView resultNum;

    private String switchSelect = "list";
    private String speciesSelect = "All species";
    private String genderSelect = "All genders";
    private String nameSearch = "";

    private int month = MainActivity.month;

    private ArrayAdapter<String> spinnerAdapterSpecies;
    private ArrayAdapter<String> spinnerAdapterGender;

    private DividerItemDecoration splitLine;

    private List<Villagers> villagers;
    private List<String> speciesList = new ArrayList<>();
    private List<String> genderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villagers);

        recyclerView = findViewById(R.id.recycleList);
        viewSwitch = findViewById(R.id.viewSwitch);
        searchName = findViewById(R.id.inputName);
        spinner_species = findViewById(R.id.speices);
        spinner_gender = findViewById(R.id.gender);
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
                speciesSelect = "All species";
                genderSelect = "All genders";
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
                villagers = searchVillagerName(villagers);

                speciesList = getSpeciesList(speciesList);
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

                genderList = getGenderList(genderList);
                spinnerAdapterGender = new ArrayAdapter<>(VillagersActivity.this, android.R.layout.simple_spinner_item, genderList);
                spinner_gender.setAdapter(spinnerAdapterGender);
                spinnerAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                if (genderList.indexOf(genderSelect) != -1) {
                    spinner_gender.setSelection(genderList.indexOf(genderSelect), true);
                } else {
                    int position_location = spinnerAdapterGender.getPosition(genderSelect);
                    spinner_gender.setSelection(position_location);
                }

                spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String genderSelectNew = (String) parent.getItemAtPosition(position);
                        if (!genderSelect.equals(genderSelectNew)) {
                            genderSelect = genderSelectNew;
                            getData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                villagers = searchGender(villagers);
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

        if (genderSelect.equals("All genders"))
            return villagers;

        for (Villagers villager : villagers)
            if (villager.getGender().equals(genderSelect))
                result.add(villager);

        return result;
    }

    private List<String> getGenderList(List<String> genderList) {
        List<String> genders = new ArrayList<>();
        genders.add("All genders");
        for (Villagers villager : villagers)
            if (!genders.contains(villager.getGender()))
                genders.add(villager.getGender());
        return genders;
    }

    private List<Villagers> searchSpecies(List<Villagers> villagers) {
        List<Villagers> result = new ArrayList<>();

        if (speciesSelect.equals("All species"))
            return villagers;

        for (Villagers villager : villagers)
            if (villager.getSpecies().equals(speciesSelect))
                result.add(villager);

        return result;

    }

    private List<String> getSpeciesList(List<String> speciesList) {
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

    public void villagersButton(View view) {
        Intent intent = new Intent(this, VillagersActivity.class);
        startActivity(intent);
    }

    public void songsButton(View view) {
        Intent intent = new Intent(this, SongsActivity.class);
        startActivity(intent);
    }
}