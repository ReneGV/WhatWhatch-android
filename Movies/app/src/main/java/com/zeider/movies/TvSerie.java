package com.zeider.movies;

/**
 * Created by Zeider on 07/02/2018.
 */

public class TvSerie extends VideoMedia {
    int serie_id;
    int season_amount;
    int chapters_amount;
    int chapter_length;

    // Contructor for catalog
    public TvSerie(int video_media_id, String name, String image_url){
        super(image_url, name);
        this.video_media_id = video_media_id;
        this.name = name;
    }


    public TvSerie(int serie_id, String name, String description, String image_description_url, String release_date, String gender_name, int season_amount, int chapters_amount){
        super(name,release_date,description,image_description_url,gender_name);
        this.serie_id = serie_id;
        this.season_amount = season_amount;
        this.chapter_length = chapters_amount;
    }
}