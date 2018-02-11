package com.varchar.whatwatch.model;

public class Serie extends VideoMedia {

    private int serieId;
    private int seasonAmount;
    private int chaptersAmount;
    private int chapter_length;

    public Serie(){};

    public int getSerieId() {
        return serieId;
    }

    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }

    public int getSeasonAmount() {
        return seasonAmount;
    }

    public void setSeasonAmount(int seasonAmount) {
        this.seasonAmount = seasonAmount;
    }

    public int getChaptersAmount() {
        return chaptersAmount;
    }

    public void setChaptersAmount(int chaptersAmount) {
        this.chaptersAmount = chaptersAmount;
    }

    public int getChapter_length() {
        return chapter_length;
    }

    public void setChapter_length(int chapter_length) {
        this.chapter_length = chapter_length;
    }
}