package com.zeider.movies;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zeider on 07/02/2018.
 */

public class Movie extends  VideoMedia {
    int movie_id;
    int length;

    //Constructor for catalog
    public Movie(int video_media_id, String name, String image_url, int movie_length){
        super(image_url, name);
        this.video_media_id = video_media_id;
        this.name = name;
    }

    //Constructor to details view
    public Movie(int movie_id, String name, String description, String image_description_url, int movie_length, String release_date, String gender_name){
        super(name, release_date, description, image_description_url, gender_name);
        this.movie_id = movie_id;
        this.length = movie_length;
    }

    //TODO obtain Movies by id



}
