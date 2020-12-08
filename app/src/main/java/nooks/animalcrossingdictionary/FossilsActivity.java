package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.List;

import nooks.animalcrossingdictionary.adapter.AdapterFossils;
import nooks.animalcrossingdictionary.entities.fish.Fish;
import nooks.animalcrossingdictionary.entities.fossils.Fossils;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class FossilsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;

    private List<Fossils> fossils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fossils);

        recyclerView = findViewById(R.id.recycleList);

        editText = findViewById(R.id.inputName);

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

                List<Fossils> fossilsSearch = searchResult(fossils);

                AdapterFossils adapterFossils = new AdapterFossils(fossilsSearch);
                recyclerView.setLayoutManager(new LinearLayoutManager(FossilsActivity.this));
                recyclerView.setAdapter(adapterFossils);

                Log.d("Retrofit", "Success: "+ response.body().get(0).getName().getNameEUen());
                Log.d("Retrofit", "Success: "+ response.body().get(0));
                Log.d("Retrofit", "Success: "+ response.body().get(0).getPrice());
                Log.d("Retrofit", "Success: "+ response.body().get(0).getMuseumPhrase());
                Log.d("Retrofit", "Success: "+ response.body().get(0).getImage_uri());
                Log.d("Retrofit", "Success: "+ response.body().size());
            }

            @Override
            public void onFailure(Call<List<Fossils>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

    public void search(View view) {
        getData();
        clearFocus();
    }



    private List<Fossils> searchResult(List<Fossils> fossilsSource) {
        List<Fossils> fossilsResult = new ArrayList<>();
        if(!TextUtils.isEmpty(editText.getText())) {
            for (Fossils fossils: fossilsSource) {
                if(fossils.getName().getNameEUen().contains(editText.getText().toString())) {
                    fossilsResult.add(fossils);
                }
            }
        } else {
            fossilsResult = fossilsSource;
        }

        return fossilsResult;
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

}