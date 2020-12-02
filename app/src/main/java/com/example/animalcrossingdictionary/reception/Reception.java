package com.example.animalcrossingdictionary.reception;

import com.example.animalcrossingdictionary.entities.Fish;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reception {

    public static void getUrl(String type) {
        String url = com.example.animalcrossingdictionary.MainActivity.API_URL + "/" + type;
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://acnhapi.com/v1/fossils/1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        GetRequest get = retrofit.create(GetRequest.class);
        Call<Fish> fishCall = get.getFish();
        fishCall.enqueue(new Callback<Fish>() {
            @Override
            public void onResponse(Call<Fish> call, Response<Fish> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<Fish> call, Throwable t) {

            }
        });
    }

}
