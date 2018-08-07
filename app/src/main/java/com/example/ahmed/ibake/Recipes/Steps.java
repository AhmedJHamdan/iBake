package com.example.ahmed.ibake.Recipes;

import java.io.Serializable;

public class Steps implements Serializable {

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoURL;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoURL = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailURL;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailURL = thumbnailUrl;
    }
}
