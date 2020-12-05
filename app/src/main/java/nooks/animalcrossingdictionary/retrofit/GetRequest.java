package nooks.animalcrossingdictionary.retrofit;

import java.util.List;

import nooks.animalcrossingdictionary.entities.bugs.Bugs;
import nooks.animalcrossingdictionary.entities.fish.Fish;
import nooks.animalcrossingdictionary.entities.fossils.Fossils;
import nooks.animalcrossingdictionary.entities.seaCreatures.SeaCreatures;
import nooks.animalcrossingdictionary.entities.songs.Songs;
import nooks.animalcrossingdictionary.entities.villagers.Villagers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRequest {

    String URL = "https://acnhapi.com/v1a/";

    //fish
    @GET("fish/")
    Call<List<Fish>> getFishes();

    @GET("fish/{fishId}")
    Call<Fish> getFishById(@Path("fishId") int fishId);

    @GET("fish/")
    Call<ResponseBody> getString();

    //Sea Creatures
    @GET("sea/")
    Call<List<SeaCreatures>> getSeaCreatures();

    //Bugs
    @GET("bugs/")
    Call<List<Bugs>> getBugs();

    //Fossils
    @GET("fossils/")
    Call<List<Fossils>> getFossils();

    //Villagers
    @GET("villagers/")
    Call<List<Villagers>> getVillagers();

    //Songs
    @GET("Songs/")
    Call<List<Songs>> getSongs();

}
