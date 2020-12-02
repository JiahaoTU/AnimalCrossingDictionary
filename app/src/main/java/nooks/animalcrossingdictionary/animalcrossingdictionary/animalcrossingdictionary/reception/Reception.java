package nooks.animalcrossingdictionary.animalcrossingdictionary.animalcrossingdictionary.reception;

import android.util.Log;

import nooks.animalcrossingdictionary.animalcrossingdictionary.animalcrossingdictionary.entities.FishList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reception {

    public static void getUrl() {
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(GetRequest.URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        GetRequest get = retrofit.create(GetRequest.class);
        Call<FishList> fishCall = get.getFish();
        fishCall.enqueue(new Callback<FishList>() {
            @Override
            public void onResponse(Call<FishList> call, Response<FishList> response) {

                Log.d("Retrofit", "Success");
            }

            @Override
            public void onFailure(Call<FishList> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }

}
