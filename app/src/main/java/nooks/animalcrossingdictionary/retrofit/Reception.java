package nooks.animalcrossingdictionary.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import nooks.animalcrossingdictionary.entities.fish.Fish;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reception {

    public static void getJsonFish() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GetRequest.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest get = retrofit.create(GetRequest.class);
        get.getString().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                ResponseBody fish = response.body();
                String jsonStr = null;
                try {
                    jsonStr = new String(response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(fish);
                String a = new String();
                Log.d("Retrofit", "Success: "+ jsonStr);
                Log.d("Retrofit", "Success: "+ fish);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Retrofit", "Failure: " + t.getMessage());
            }
        });
    }



}
