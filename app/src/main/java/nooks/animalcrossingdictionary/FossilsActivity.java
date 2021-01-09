package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.List;

import nooks.animalcrossingdictionary.adapter.AdapterFossils;
import nooks.animalcrossingdictionary.entities.fossils.Fossils;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class FossilsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText searchName;

    private String nameSearch = "";

    private List<Fossils> fossils;
    List<Fossils> fossilsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fossils);

        recyclerView = findViewById(R.id.recycleList);
        searchName = findViewById(R.id.inputName);

        getData();
    }

    private void getData() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GetRequest.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest get = retrofit.create(GetRequest.class);
        get.getFossils().enqueue(new Callback<List<Fossils>>() {
            @Override
            public void onResponse(Call<List<Fossils>> call, Response<List<Fossils>> response) {
                fossils = response.body();

                fossilsAdapter = fossils;
                fossilsAdapter = resultName(fossilsAdapter);

                AdapterFossils adapterGrid = new AdapterFossils(fossilsAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(FossilsActivity.this, 3));
                recyclerView.setAdapter(adapterGrid);

            }

            @Override
            public void onFailure(Call<List<Fossils>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
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

    private List<Fossils> resultName(List<Fossils> fossilsList) {
        List<Fossils> result = new ArrayList<>();

        if(nameSearch.equals(""))
            return fossilsList;
        for (Fossils fossils : fossilsList) {
            if ((fossils.getName().getNameEUen().toLowerCase()).contains(nameSearch)) {
                result.add(fossils);
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