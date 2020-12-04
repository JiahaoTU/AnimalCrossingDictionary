package nooks.animalcrossingdictionary.entities.common;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("name-EUen")
    private String nameEUen;
    @SerializedName("name-EUfr")
    private String nameEUfr;

    public Name() {

    }

    public String getNameEUen() {
        return nameEUen;
    }

    public void setNameEUen(String nameEUen) {
        this.nameEUen = nameEUen;
    }

    public String getNameEUfr() {
        return nameEUfr;
    }

    public void setNameEUfr(String nameEUfr) {
        this.nameEUfr = nameEUfr;
    }
}
