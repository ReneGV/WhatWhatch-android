package com.varchar.whatwatch.model;

import com.varchar.whatwatch.model.Gender;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zeider on 07/02/2018.
 */

public class VideoMedia {
    int video_media_id;
    String name;
    String release_date;
    String description;
    URL image_url;
    URL image_description_url;
    Gender gender;

    public VideoMedia(String name, String image_url) {
        this.name = name;
        URL url = null;
        try {
            url = new URL(image_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.image_url = url;
    }

    public VideoMedia(String name, String release_date, String description, String image_description_url, String gender_name) {
        this.name = name;
        this.release_date = release_date;
        this.description = description;
        try {
            this.image_description_url = new URL(image_description_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.gender = new Gender(gender_name);
    }


    //TODO functions getCollectionViewImages and getDetailImage
}
