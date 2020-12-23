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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.animalcrossingdictionary.R;

import java.sql.BatchUpdateException;
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
    private EditText editText;
    private RadioGroup radioGroup;

    private int switchSelect = 1;
    private String radioSelect = "";

    private int month = MainActivity.month;

    private DividerItemDecoration splitLine;

    private List<Bugs> bugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugs);

        recyclerView = findViewById(R.id.recycleList);
        viewSwitch = findViewById(R.id.viewSwitch);
        editText = findViewById(R.id.inputName);
        radioGroup = findViewById(R.id.radioGroup);

        splitLine = new DividerItemDecoration(BugsActivity.this, DividerItemDecoration.VERTICAL);

        viewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                    switchSelect = 1;
                else
                    switchSelect = 2;
                getData();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

                List<Bugs> bugsAdapter = new ArrayList<>();
                bugsAdapter = resultNorthSouth(bugs);


                AdapterBugs adapterList = new AdapterBugs(bugsAdapter, switchSelect);
                AdapterBugs adapterGrid = new AdapterBugs(bugsAdapter, switchSelect);


                if(switchSelect == 1) {
                    recyclerView.removeItemDecoration(splitLine);
                    setListView(adapterList);
                }else if(switchSelect == 2) {
                    setGridView(adapterGrid);
                }
            }

            @Override
            public void onFailure(Call<List<Bugs>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

    private void setListView(AdapterBugs adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(BugsActivity.this));
        recyclerView.addItemDecoration(splitLine);
        recyclerView.setAdapter(adapter);
    }

    private void setGridView(AdapterBugs adapter) {
        recyclerView.setLayoutManager(new GridLayoutManager(BugsActivity.this, 3));
        recyclerView.removeItemDecoration(splitLine);
        recyclerView.setAdapter(adapter);
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

    public void search(View view) {
        clearFocus();
    }

    private void clearFocus(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        editText.clearFocus();
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