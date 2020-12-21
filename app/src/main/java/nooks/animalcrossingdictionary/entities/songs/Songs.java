package nooks.animalcrossingdictionary.entities.songs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nooks.animalcrossingdictionary.entities.common.Name;

public class Songs implements Serializable {

    private String id;
    @SerializedName("file-name")
    private String fileName;
    private Name name;
    @SerializedName("buy-price")
    private int buyPrice;
    private Boolean isOrderable;
    private String music_uri;
    private String image_uri;

    public Songs() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Boolean getOrderable() {
        return isOrderable;
    }

    public void setOrderable(Boolean orderable) {
        isOrderable = orderable;
    }

    public String getMusic_uri() {
        return music_uri;
    }

    public void setMusic_uri(String music_uri) {
        this.music_uri = music_uri;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
