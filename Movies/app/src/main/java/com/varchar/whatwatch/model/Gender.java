package com.varchar.whatwatch.model;

import java.util.ArrayList;
import java.util.List;

public class Gender {

    private int id;
    private String name;
    private List<VideoMedia> moviesList;

    public Gender() {
        name = "undefined";
        moviesList = new ArrayList<>();
    }

    public Gender(String name) {
        this();
        this.name = name;
    }
}
