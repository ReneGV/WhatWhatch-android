package com.varchar.whatwatch.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Genre implements Serializable{

    private int id;
    private String name;
    private List<VideoMedia> videoMediaList;

    public Genre() {
        name = "undefined";
        videoMediaList = new ArrayList<>();
    }

    public Genre(String name) {
        this();
        this.name = name;
    }

    public static Genre fromJSON(String name, JSONArray jsonMovies/*,VideoMedia.VideoType videoType*/) throws JSONException {
        Genre genre = new Genre(name);
            for (int movieIndex = 0; movieIndex < jsonMovies.length(); movieIndex++) {
                JSONObject jsonMovie = jsonMovies.getJSONObject(movieIndex);
                VideoMedia videoMedia = new VideoMedia(
                        jsonMovie.getInt("id"),
                        jsonMovie.getString("name"),
                        jsonMovie.getString("collectionViewImage"));
                //videoMedia.setType(videoType);
                genre.getVideoMediaList().add(videoMedia);

            }
        return genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VideoMedia> getVideoMediaList() {
        return videoMediaList;
    }

    public void setVideoMediaList(List<VideoMedia> videoMediaList) {
        this.videoMediaList = videoMediaList;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videoMediaList=" + videoMediaList +
                '}';
    }
}
