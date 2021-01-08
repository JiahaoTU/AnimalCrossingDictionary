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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nooks.animalcrossingdictionary.adapter.AdapterBugs;
import nooks.animalcrossingdictionary.entities.bugs.Bugs;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BugsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Switch viewSwitch;
    private EditText searchName;
    private RadioGroup nsChooseRadio;
    private Spinner spinner_location, spinner_rarity;

    private String switchSelect = "";
    private String radioSelect = "";
    private String locationSelect = "";
    private String raritySelect = "";
    private String nameSearch = "";

    private int month = MainActivity.month;

    private ArrayAdapter<String> spinnerAdapterLocation;
    private ArrayAdapter<String> spinnerAdapterRarity;

    private DividerItemDecoration splitLine;

    private List<Bugs> bugs;
    private List<String> locationList = new ArrayList<>();
    private List<String> rarityList = new ArrayList<>();
    List<Bugs> bugsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugs);

        recyclerView = findViewById(R.id.recycleList);
        viewSwitch = findViewById(R.id.viewSwitch);
        searchName = findViewById(R.id.inputName);
        nsChooseRadio = findViewById(R.id.radioGroup);
        spinner_location = findViewById(R.id.location_spinner);
        spinner_rarity = findViewById(R.id.rarity_spinner);

        splitLine = new DividerItemDecoration(BugsActivity.this, DividerItemDecoration.VERTICAL);

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

        getData();

    }

    private void getData() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GetRequest.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest get = retrofit.create(GetRequest.class);
        get.getBugs().enqueue(new Callback<List<Bugs>>() {
            @Override
            public void onResponse(Call<List<Bugs>> call, Response<List<Bugs>> response) {
                bugs = response.body();

                bugsAdapter = bugs;
                bugsAdapter = resultNorthSouth(bugsAdapter);
                bugsAdapter = resultName(bugsAdapter);

                locationList = getLocationList(bugsAdapter);
                locationSelect = locationList.get(0);
                spinnerAdapterLocation = new ArrayAdapter<>(BugsActivity.this, android.R.layout.simple_spinner_item, locationList);
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

                bugsAdapter = resultLocation(bugsAdapter);

                rarityList = getRarityList(bugsAdapter);
                raritySelect = rarityList.get(0);
                spinnerAdapterRarity = new ArrayAdapter<>(BugsActivity.this, android.R.layout.simple_spinner_item, rarityList);
                spinner_rarity.setAdapter(spinnerAdapterRarity);
                spinnerAdapterRarity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                int position_rarity = spinnerAdapterRarity.getPosition(locationSelect);
                spinner_location.setSelection(position_rarity);

                if(rarityList.indexOf(raritySelect) != -1) {
                    spinner_rarity.setSelection(rarityList.indexOf(raritySelect), true);
                } else {
                    spinner_rarity.setSelection(0, true);
                }

                spinner_rarity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String raritySelectNew = (String) parent.getItemAtPosition(position);
                        if(!raritySelect.equals(raritySelectNew)) {
                            raritySelect = raritySelectNew;
                            getData();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                bugsAdapter = resultRarity(bugsAdapter);


                AdapterBugs adapterList = new AdapterBugs(bugsAdapter, switchSelect);
                AdapterBugs adapterGrid = new AdapterBugs(bugsAdapter, switchSelect);
                if(switchSelect.equals("") || switchSelect.equals("list")) {
                    recyclerView.removeItemDecoration(splitLine);
                    recyclerView.setLayoutManager(new LinearLayoutManager(BugsActivity.this));
                    recyclerView.addItemDecoration(splitLine);
                    recyclerView.setAdapter(adapterList);
                }else if(switchSelect.equals("grid")) {
                    recyclerView.setLayoutManager(new GridLayoutManager(BugsActivity.this, 3));
                    recyclerView.removeItemDecoration(splitLine);
                    recyclerView.setAdapter(adapterGrid);
                }
            }

            @Override
            public void onFailure(Call<List<Bugs>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

    private List<String> getLocationList(List<Bugs> bugsList){
        List<String> locations = new ArrayList<>();
        locations.add("All locations");
        for(Bugs bugs: bugsList) {
            if(!locations.contains(bugs.getAvailability().getLocation()))
                locations.add(bugs.getAvailability().getLocation());
        }
        return locations;
    }

    private List<String> getRarityList(List<Bugs> bugsList) {
        List<String> rarities = new ArrayList<>();
        rarities.add("All rarities");
        for(Bugs bugs: bugsList) {
            if(!rarities.contains(bugs.getAvailability().getRarity()))
                rarities.add(bugs.getAvailability().getRarity());
        }
        return rarities;
    }

    private List<Bugs> resultNorthSouth(List<Bugs> bugsList) {
        List<Bugs> result = new ArrayList<>();

        if(radioSelect.equals("All") || radioSelect.equals(""))
            return bugsList;

        for(Bugs bugs: bugsList) {
            List<Integer> monthList = new ArrayList<>();
            if(radioSelect.equals("Northern")) {
                monthList = Arrays.stream(bugs.getAvailability().getMonthArrayNorthern()).boxed().collect(Collectors.toList());
            }else if(radioSelect.equals("Southern")) {
                monthList = Arrays.stream(bugs.getAvailability().getMonthArraySouthern()).boxed().collect(Collectors.toList());
            }

            if(monthList.contains(month)) {
                result.add(bugs);
            }
        }

        return result;
    }

    private List<Bugs> resultLocation(List<Bugs> bugsList) {
        List<Bugs> result = new ArrayList<>();

        if(locationSelect.equals("All locations") || locationSelect.equals(""))
            return bugsList;

        for(Bugs bugs: bugsList) {
            if(bugs.getAvailability().getLocation().equals(locationSelect))
                result.add(bugs);
        }

        return result;
    }

    private List<Bugs> resultRarity(List<Bugs> bugsList) {
        List<Bugs> result = new ArrayList<>();

        if(raritySelect.equals("All rarities") || raritySelect.equals(""))
            return bugsList;

        for(Bugs bugs: bugsList) {
            if(bugs.getAvailability().getRarity().equals(raritySelect))
                result.add(bugs);
        }

        return result;
    }

    public void search(View view) {
        nameSearch = searchName.getText().toString();
        clearFocus();
        getData();
    }

    private void clearFocus(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        searchName.clearFocus();
    }

    private List<Bugs> resultName(List<Bugs> bugsList) {
        List<Bugs> result = new ArrayList<>();

        if(nameSearch.equals(""))
            return bugsList;
        for (Bugs bugs : bugsList) {
            if (bugs.getName().getNameEUen().contains(nameSearch)) {
                result.add(bugs);
            }
        }

        return result;
    }

    public void fishButton(View view){
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