package com.example.sjha3.networkapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sjha3 on 7/31/17.
 */

public class ListObject {
    private String song_name;
    // either name the name of the variable same as the json object or add serialized name here
    @SerializedName("song_id")
    private String song_year;
    @SerializedName("artist_name")
    private String song_artist;

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_year() {
        return song_year;
    }

    public void setSong_year(String song_year) {
        this.song_year = song_year;
    }

    public String getSong_artist() {
        return song_artist;
    }

    public void setSong_artist(String song_artist) {
        this.song_artist = song_artist;
    }
}
