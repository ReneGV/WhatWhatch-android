package com.varchar.whatwatch.model;

import android.provider.MediaStore;

import com.varchar.whatwatch.R;

import org.w3c.dom.ProcessingInstruction;

import java.io.Serializable;

//FIXME: DELETE HARDCODED IMAGES ID
public class VideoMedia implements Serializable{

    public static enum VideoType{
        SERIE("serie"),
        MOVIE("movie");

        private  String typeName;
        VideoType(String typeName){
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return this.typeName;
        }
    }

    private static final long serialVersionUID = 8799656478674716638L;

    private int id;
    private String name;
    private String releaseDate;
    private String description;
    private String imageUrl;
    private String detailImageUrl;
    private Genre gender;
   private VideoType type;

    //TODO: DELETE THIS IMAGE ID, AND FETCH THE IMAGE FROM WS
    private int imageId;
    //TODO: DELETE THIS GETTET
    public int getImageId() {
        return this.imageId == 0 ? R.drawable.poster_default : imageId;
    }
    public void setImageId(int imageId) {this.imageId = imageId;}

    //TODO: DELETE THIS STATIC FACTORY METHOD
    // Set image from local drawable resources
    public static VideoMedia fromLocalResources(int imageId, String name){
        VideoMedia videoMedia = new VideoMedia();
        videoMedia.imageId = imageId;
        videoMedia.name = name;
        return videoMedia;
    }

    public VideoMedia(){}

    public VideoMedia(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public VideoMedia(String name, String releaseDate, String description, String detailImageUrl, String gender_name) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        this.detailImageUrl = detailImageUrl;
        this.gender = new Genre(gender_name);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetailImageUrl() {
        return detailImageUrl;
    }

    public void setDetailImageUrl(String detailImageUrl) {
        this.detailImageUrl = detailImageUrl;
    }

    public Genre getGender() {
        return gender;
    }

    public void setGender(Genre gender) {
        this.gender = gender;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    public void setType(String type) {
        if (type.equals("movie")){
            this.type = VideoType.MOVIE;
        }else if (type.equals("serie")){
            this.type = VideoType.SERIE;
        }
    }


    @Override
    public String toString() {
        return "VideoMedia{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL=" +imageUrl + '\'' +
                '}';
    }

}
