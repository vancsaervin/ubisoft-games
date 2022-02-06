package com.unitbv.ubisoft;

import java.io.Serializable;

public class Game implements Serializable {
    private String id, name, releaseDate, image, gallery, url, shortDescription, longDescription;

    // constructor
    public Game(String id, String name, String releaseDate, String image, String gallery, String url, String shortDescription, String longDescription) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.image = image;
        this.gallery = gallery;
        this.url = url;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    // getters & setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
