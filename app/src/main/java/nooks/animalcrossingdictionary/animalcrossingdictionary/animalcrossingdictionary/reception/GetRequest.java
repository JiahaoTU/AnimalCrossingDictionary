package nooks.animalcrossingdictionary.animalcrossingdictionary.animalcrossingdictionary.reception;

import java.util.List;

import nooks.animalcrossingdictionary.animalcrossingdictionary.animalcrossingdictionary.entities.Fish;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {

    String URL = "https://acnhapi.com/v1/";

    @GET("fish/")
    Call<Fish> getFishes();

}
