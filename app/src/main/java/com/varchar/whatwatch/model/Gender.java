package com.varchar.whatwatch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gender implements Serializable{

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
