package nooks.animalcrossingdictionary.entities.fossils;

import com.google.gson.annotations.SerializedName;

import nooks.animalcrossingdictionary.entities.common.Availability;
import nooks.animalcrossingdictionary.entities.common.Name;

public class Fossils {

    private String id;
    @SerializedName("file-name")
    private String fileName;
    private Name name;
    private int price;
    @SerializedName("museum-phrase")
    private String museumPhrase;
    private String image_uri;

    public Fossils() {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
