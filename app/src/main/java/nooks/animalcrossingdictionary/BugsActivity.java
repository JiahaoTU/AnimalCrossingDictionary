package nooks.animalcrossingdictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.animalcrossingdictionary.R;

import java.util.List;

import nooks.animalcrossingdictionary.entities.bugs.Bugs;
import nooks.animalcrossingdictionary.retrofit.GetRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BugsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Bugs> bugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bugs);

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

                //List<Fish> fishSearch = searchResult(fishes);

                /*Adapter adapter = new Adapter(fishSearch);
                recyclerView.setLayoutManager(new LinearLayoutManager(FishActivity.this));
                recyclerView.setAdapter(adapter);*/

                Log.d("Retrofit", "Success: "+ response.body().get(0).getFileName());
                Log.d("Retrofit", "Success: "+ response.body().get(0).getId());
                Log.d("Retrofit", "Success: "+ response.body().get(0).getPrice());
                Log.d("Retrofit", "Success: "+ response.body().get(0).getCatchPhrase());
                Log.d("Retrofit", "Success: "+ response.body().get(0).getAvailability().getLocation());
                Log.d("Retrofit", "Success: "+ response.body().size());
            }

            @Override
            public void onFailure(Call<List<Bugs>> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }
}