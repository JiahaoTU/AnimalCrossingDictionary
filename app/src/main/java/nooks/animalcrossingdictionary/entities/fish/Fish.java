package nooks.animalcrossingdictionary.entities.fish;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nooks.animalcrossingdictionary.entities.common.Availability;
import nooks.animalcrossingdictionary.entities.common.Name;

public class Fish implements Serializable {

    private String id;
    @SerializedName("file-name")
    private String fileName;
    private Name name;
    private Availability availability;
    private String shadow;
    private int price;
    @SerializedName("catch-phrase")
    private String catchPhrase;
    @SerializedName("museum-phrase")
    private String museumPhrase;
    private String image_uri;
    private String icon_uri;

    public Fish() {
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

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getShadow() {
        return shadow;
    }

    public void setShadow(String shadow) {
        this.shadow = shadow;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getMuseumPhrase() {
        return museumPhrase;
    }

    public void setMuseumPhrase(String museumPhrase) {
        this.museumPhrase = museumPhrase;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public String getIcon_uri() {
        return icon_uri;
    }

    public void setIcon_uri(String icon_uri) {
        this.icon_uri = icon_uri;
    }

}
