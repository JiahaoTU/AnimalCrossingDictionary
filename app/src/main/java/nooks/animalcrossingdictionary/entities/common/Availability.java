package nooks.animalcrossingdictionary.entities.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Availability implements Serializable {

    @SerializedName("month-northern")
    private String mouthNorthern;
    @SerializedName("month-southern")
    private String mouthSouthern;
    private String time;
    private Boolean isAllDay;
    private Boolean isAllYear;
    private String location;    //only fish, bugs
    private String rarity;  //only fish, bugs
    @SerializedName("month-array-northern")
    private int[] monthArrayNorthern;
    @SerializedName("month-array-southern")
    private int[] monthArraySouthern;
    @SerializedName("time-array")
    private int[] timeArray;

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

    public int[] getMonthArrayNorthern() {
        return monthArrayNorthern;
    }

    public void setMonthArrayNorthern(int[] monthArrayNorthern) {
        this.monthArrayNorthern = monthArrayNorthern;
    }

    public int[] getMonthArraySouthern() {
        return monthArraySouthern;
    }

    public void setMonthArraySouthern(int[] monthArraySouthern) {
        this.monthArraySouthern = monthArraySouthern;
    }

    public int[] getTimeArray() {
        return timeArray;
    }

    public void setTimeArray(int[] timeArray) {
        this.timeArray = timeArray;
    }
}
