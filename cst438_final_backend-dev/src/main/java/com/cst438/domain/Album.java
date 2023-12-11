package com.cst438.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Represents an album in the Spotify API response
@JsonIgnoreProperties(ignoreUnknown = true)

public class Album {
    private String album_type;
    private Artist[] artists;
    private String name;
    private String release_date;

    public Artist[] getArtists() {
        return artists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }
    // other fields...

    // Getters and setters
    // ...
}
