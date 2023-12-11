package com.cst438.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Track {
    private String id;
    private String name;
    private Album album;
    private int duration_ms;

    // Other fields...

    // Default constructor
    public Track() {}

    // Getters and setters
    // ...

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public int getDuration_ms() {
    	return duration_ms;
    }
    
    public void setDuration(int duration_ms) {
    	this.duration_ms = duration_ms;
    }
}

