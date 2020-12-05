package nooks.animalcrossingdictionary.entities.seaCreatures;

import com.google.gson.annotations.SerializedName;

import nooks.animalcrossingdictionary.entities.common.Availability;
import nooks.animalcrossingdictionary.entities.common.Name;

public class SeaCreatures {

    private String id;
    @SerializedName("file-name")
    private String fileName;
    private Name name;
    private Availability availability;
    private String speed;
    private String shadow;
    private int price;
    @SerializedName("catch-phrase")
    private String catchPhrase;
    private String image_uri;
    private String icon_uri;
    @SerializedName("museum-phrase")
    private String museumPhrase;

    public SeaCreatures() {

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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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

    public String getMuseumPhrase() {
        return museumPhrase;
    }

    public void setMuseumPhrase(String museumPhrase) {
        this.museumPhrase = museumPhrase;
    }
}
