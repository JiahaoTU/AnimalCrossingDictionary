package nooks.animalcrossingdictionary.retrofit;

import java.util.List;

import nooks.animalcrossingdictionary.entities.fish.Fish;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetRequest {

    String URL = "https://acnhapi.com/v1a/";

    @GET("fish/")
    Call<List<Fish>> getFishes();

    @GET("fish/{fishId}")
    Call<Fish> getFishById(@Path("fishId") int fishId);

    @GET("fish/")
    Call<ResponseBody> getString();


}
