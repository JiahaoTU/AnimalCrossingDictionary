package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;

import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nooks.animalcrossingdictionary.adapter.AdapterFish;
import nooks.animalcrossingdictionary.adapter.SwitchButton;
import nooks.animalcrossingdictionary.entities.fish.Fish;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class FishActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //private Switch viewSwitch;
    private SwitchButton viewSwitch;
    private EditText searchName;
    private RadioGroup nsChooseRadio;
    private Spinner spinner_location, spinner_rarity;
    private Button searchButton, resetButton;
    private TextView resultNum;

    private String switchSelect = "list";
    private String radioSelect = "All";
    private String locationSelect = "All locations";
    private String raritySelect = "All rarities";
    private String nameSearch = "";

    private int month = MainActivity.month;

    private ArrayAdapter<String> spinnerAdapterLocation;
    private ArrayAdapter<String> spinnerAdapterRarity;

    private DividerItemDecoration splitLine;

    private List<Fish> fishes;
    private List<String> locationList = new ArrayList<>();
    private List<String> rarityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
        recyclerView = findViewById(R.id.recycleList);
        viewSwitch = findViewById(R.id.viewSwitch);
        searchName = findViewById(R.id.inputName);
        nsChooseRadio = findViewById(R.id.radioGroup);
        spinner_location = findViewById(R.id.location);
        spinner_rarity = findViewById(R.id.rarity);
        searchButton = findViewById(R.id.searchButton);
        resetButton = findViewById(R.id.reset);
        resultNum = findViewById(R.id.resultNum);

        splitLine = new DividerItemDecoration(FishActivity.this, DividerItemDecoration.VERTICAL);

        viewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
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
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm.isActive()&&getCurrentFocus()!=null){
                    if (getCurrentFocus().getWindowToken()!=null) {
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
                locationSelect = "All locations";
                raritySelect = "All rarities";
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
        get.getFishes().enqueue(new Callback<List<Fish>>() {
            @Override
            public void onResponse(Call<List<Fish>> call, Response<List<Fish>> response) {
                List<Fish> fishResponse = response.body();

                fishes = fishResponse;
                fishes = searchNorthSouth(fishes);
                fishes = searchFishName(fishes);

                locationList = getLocationList(fishes);
                spinnerAdapterLocation = new ArrayAdapter<>(FishActivity.this, android.R.layout.simple_spinner_item, locationList);
                spinner_location.setAdapter(spinnerAdapterLocation);
                spinnerAdapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                if(locationList.indexOf(locationSelect) != -1) {
                    spinner_location.setSelection(locationList.indexOf(locationSelect), true);
                } else {
                    int position_location = spinnerAdapterLocation.getPosition(locationSelect);
                    spinner_location.setSelection(position_location);
                }

                spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String locationSelectNew = (String) parent.getItemAtPosition(position);
                        if(!locationSelect.equals(locationSelectNew)) {
                            locationSelect = locationSelectNew;
                            getData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                fishes = searchLocation(fishes);

                rarityList = getRarityList(fishes);
                spinnerAdapterRarity = new ArrayAdapter<>(FishActivity.this, android.R.layout.simple_spinner_item, rarityList);
                spinner_rarity.setAdapter(spinnerAdapterRarity);
                spinnerAdapterRarity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                if(rarityList.indexOf(radioSelect) != -1) {
                    spinner_rarity.setSelection(rarityList.indexOf(raritySelect), true);
                } else {
                    int position_location = spinnerAdapterRarity.getPosition(raritySelect);
                    spinner_rarity.setSelection(position_location);
                }

                spinner_rarity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String locationSelectNew = (String) parent.getItemAtPosition(position);
                        if(!raritySelect.equals(locationSelectNew)) {
                            raritySelect = locationSelectNew;
                            getData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                fishes = searchRarity(fishes);
                AdapterFish adapterFish = new AdapterFish(fishes, switchSelect);
                resultNum.setText(adapterFish.getItemCount() + " results");
                if(switchSelect.equals("list")) {
                    recyclerView.removeItemDecoration(splitLine);
                    recyclerView.setLayoutManager(new LinearLayoutManager(FishActivity.this));
                    recyclerView.addItemDecoration(splitLine);
                    recyclerView.setAdapter(adapterFish);
                }else if(switchSelect.equals("grid")) {
                    recyclerView.setLayoutManager(new GridLayoutManager(FishActivity.this, 3));
                    recyclerView.removeItemDecoration(splitLine);
                    recyclerView.setAdapter(adapterFish);
                }


            }

            @Override
            public void onFailure(Call<List<Fish>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

    private List<Fish> searchRarity(List<Fish> fishes) {
        List<Fish> result = new ArrayList<>();

        if(raritySelect.equals("All rarities"))
            return fishes;

        for(Fish fish : fishes) {
            if(fish.getAvailability().getRarity().equals(raritySelect))
                result.add(fish);
        }

        return result;
    }

    private List<Fish> searchLocation(List<Fish> fishes) {
        List<Fish> result = new ArrayList<>();

        if(locationSelect.equals("All locations"))
            return fishes;

        for(Fish fish : fishes) {
            if(fish.getAvailability().getLocation().equals(locationSelect))
                result.add(fish);
        }

        return result;
    }

    private List<String> getRarityList(List<Fish> fishes) {
        List<String> rarities = new ArrayList<>();
        rarities.add("All rarities");
        for (Fish fish : fishes) {
            if (!rarities.contains(fish.getAvailability().getRarity()))
                rarities.add(fish.getAvailability().getRarity());
        }
        return rarities;
    }

    private List<String> getLocationList(List<Fish> fishes) {
        List<String> locs = new ArrayList<>();
        locs.add("All locations");
        for (Fish fish : fishes) {
            if (!locs.contains(fish.getAvailability().getLocation()))
                locs.add(fish.getAvailability().getLocation());
        }
        return locs;
    }

    private List<Fish> searchFishName(List<Fish> fishes) {
        List<Fish> result = new ArrayList<>();

        if(nameSearch.equals(""))
            return fishes;

        for (Fish fish : fishes) {
            if (fish.getName().getNameEUen().contains(nameSearch)) {
                result.add(fish);
            }
        }

        return result;
    }

    private List<Fish> searchNorthSouth(List<Fish> fishes) {
        List<Fish> result = new ArrayList<>();
        if(radioSelect.equals("All"))
            return fishes;

        for (Fish fish : fishes) {
            List<Integer> monthList = new ArrayList<>();
            if(radioSelect.equals("Northern")) {
                monthList = Arrays.stream(fish.getAvailability().getMonthArrayNorthern()).boxed().collect(Collectors.toList());
            }else if(radioSelect.equals("Southern")) {
                monthList = Arrays.stream(fish.getAvailability().getMonthArraySouthern()).boxed().collect(Collectors.toList());
            }
            if (monthList.contains(month)) {
                result.add(fish);
            }
        }

        return result;
    }

    public void fishButton(View view) {
        Intent intent = new Intent(this, FishActivity.class);
        startActivity(intent);
    }

    public void bugsButton(View view){
        Intent intent = new Intent(this, BugsActivity.class);
        startActivity(intent);
    }

    public void seaCreaturesButton(View view){
        Intent intent = new Intent(this, SeaCreaturesActivity.class);
        startActivity(intent);
    }

    public void fossilsButton(View view){
        Intent intent = new Intent(this, FossilsActivity.class);
        startActivity(intent);
    }

    public void villagersButton(View view){
        Intent intent = new Intent(this, VillagersActivity.class);
        startActivity(intent);
    }

    public void songsButton(View view){
        Intent intent = new Intent(this, SongsActivity.class);
        startActivity(intent);
    }
}