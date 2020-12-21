package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.animalcrossingdictionary.R;

import java.util.ArrayList;
import java.util.List;

import nooks.animalcrossingdictionary.adapter.Adapter;
import nooks.animalcrossingdictionary.entities.fish.Fish;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class FishActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Fish> fishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);

        recyclerView = findViewById(R.id.recycleList);

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
                fishes = response.body();

                List<Fish> fishSearch = searchResult(fishes);

                Adapter adapter = new Adapter(fishSearch);
                recyclerView.setLayoutManager(new LinearLayoutManager(FishActivity.this));
                recyclerView.addItemDecoration(new DividerItemDecoration(FishActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.setAdapter(adapter);

                /*Log.d("Retrofit", "Success: "+ response.body().get(0));
                Log.d("Retrofit", "Success: "+ response.body().get(0));
                Log.d("Retrofit", "Success: "+ response.body().get(0));
                Log.d("Retrofit", "Success: "+ response.body().get(0));
                Log.d("Retrofit", "Success: "+ response.body().get(0));
                Log.d("Retrofit", "Success: "+ response.body().size());
                Log.d("Retrofit", "Success: "+ fishes.size());*/
            }

            @Override
            public void onFailure(Call<List<Fish>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

    private String shadow = null;

    public void search(View view) {
        shadow = "Smallest";
        getData();
    }



    private List<Fish> searchResult(List<Fish> fishSource) {
        List<Fish> fishResult = new ArrayList<>();
        if(shadow != null) {
            for (Fish fish: fishSource) {
                if(fish.getShadow().contains(shadow)) {
                    fishResult.add(fish);
                }
            }
        } else {
            fishResult = fishSource;
        }

        return fishResult;
    }

}