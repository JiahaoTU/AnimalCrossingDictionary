package nooks.animalcrossingdictionary.entities.fish;

import com.google.gson.annotations.SerializedName;

public class Availability {

    @SerializedName("month-northern")
    private String mouthNorthern;
    @SerializedName("month-southern")
    private String mouthSouthern;
    private String time;
    private Boolean isAllDay;
    private Boolean isAllYear;
    private String location;
    private String rarity;
    @SerializedName("month-array-northern")
    private String[] monthArrayNorthern;
    @SerializedName("month-array-southern")
    private String[] monthArraySouthern;
    @SerializedName("time-array")
    private String[] timeArray;

    public Availability(){

    }

    public String getMouthNorthern() {
        return mouthNorthern;
    }

    public void setMouthNorthern(String mouthNorthern) {
        this.mouthNorthern = mouthNorthern;
    }

    public String getMouthSouthern() {
        return mouthSouthern;
    }

    public void setMouthSouthern(String mouthSouthern) {
        this.mouthSouthern = mouthSouthern;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getAllDay() {
        return isAllDay;
    }

    public void setAllDay(Boolean allDay) {
        isAllDay = allDay;
    }

    public Boolean getAllYear() {
        return isAllYear;
    }

    public void setAllYear(Boolean allYear) {
        isAllYear = allYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String[] getMonthArrayNorthern() {
        return monthArrayNorthern;
    }

    public void setMonthArrayNorthern(String[] monthArrayNorthern) {
        this.monthArrayNorthern = monthArrayNorthern;
    }

    public String[] getMonthArraySouthern() {
        return monthArraySouthern;
    }

    public void setMonthArraySouthern(String[] monthArraySouthern) {
        this.monthArraySouthern = monthArraySouthern;
    }

    public String[] getTimeArray() {
        return timeArray;
    }

    public void setTimeArray(String[] timeArray) {
        this.timeArray = timeArray;
    }
}
