package com.varchar.whatwatch.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;

public class VideoMedia {

    private int id;
    private String name;
    private String releaseDate;
    private String description;
    private URL imageUrl;
    private URL imageDescriptionUrl;
    private Gender gender;

    public VideoMedia(){}

    public VideoMedia(String name, String imageUrl) {
        this.name = name;
        URL url = null;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.imageUrl = url;
    }

    public VideoMedia(String name, String releaseDate, String description, String imageDescriptionUrl, String gender_name) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        try {
            this.imageDescriptionUrl = new URL(imageDescriptionUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.gender = new Gender(gender_name);
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public URL getImageDescriptionUrl() {
        return imageDescriptionUrl;
    }

    public void setImageDescriptionUrl(URL imageDescriptionUrl) {
        this.imageDescriptionUrl = imageDescriptionUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
