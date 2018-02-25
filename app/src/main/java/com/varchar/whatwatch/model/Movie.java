package com.varchar.whatwatch.model;

import java.io.Serializable;

public class Movie extends  VideoMedia implements Serializable {

    private int movieId;
    private int length;

    public Movie(){}

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
