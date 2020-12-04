package nooks.animalcrossingdictionary.retrofit;

import java.util.List;

import nooks.animalcrossingdictionary.entities.fish.Fish;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    Call<Fish> searchFish(@Query("id") String shadow);

    @GET("fish/")
    Call<ResponseBody> getString();

    @POST("cards/")
    Call<QueryResponse> getCards(@Body QueryParam queryParam);

    class QueryResponse {
        private List<Fish> results;

        public List<Fish> getResults() {
            return results;
        }

        public void setResults(List<Fish> results) {
            this.results = results;
        }
    }

    class QueryParam {
        private String id;
        public QueryParam(String ip) {
            this.id = ip;
        }
    }
}
