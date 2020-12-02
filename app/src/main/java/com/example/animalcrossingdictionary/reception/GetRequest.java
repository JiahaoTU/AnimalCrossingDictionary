package com.example.animalcrossingdictionary.reception;

import com.example.animalcrossingdictionary.entities.Fish;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {

    @GET("/latest")
    Call<Fish> getFish();

}
