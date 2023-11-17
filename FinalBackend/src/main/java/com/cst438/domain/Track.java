package com.cst438.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "track")
public class Track {

    @Id
    private String id;
    private String name;
    private String artist;
    private String album;
    private int song_len;

    // Constructors
    public Track() {
    }

    public Track(String id, String name, String artist, String album, int song_len) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.song_len = song_len;

    }

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSong_len() {
        return song_len;
    }

    public void setSong_len(int song_len) {
        this.song_len = song_len;
    }
}