package nooks.animalcrossingdictionary.entities.common;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("name-EUen")
    private String nameEUen;
    @SerializedName("name-EUfr")
    private String nameEUfr;
    @SerializedName("name-CNzh")
    private String nameCNzh;

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

    public String getNameCNzh() {
        return nameCNzh;
    }

    public void setNameCNzh(String nameCNzh) {
        this.nameCNzh = nameCNzh;
    }
}
