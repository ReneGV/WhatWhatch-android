package com.zeider.movies;

/**
 * Created by Zeider on 08/02/2018.
 */

public class Gender {
    int id;
    String name="undefined";
    Movie[] moviesList;
    TvSerie[] seriesList;

    public Gender() {
    }

    public Gender(String name) {
        this.name = name;
    }
}
