package com.example.animalcrossingdictionary.reception;

import com.example.animalcrossingdictionary.entities.Fish;
import com.example.animalcrossingdictionary.entities.FishList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {

    String URL = "https://acnhapi.com/v1/";

    @GET("fish/")
    Call<FishList> getFish();

}
