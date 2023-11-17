package com.cst438.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    private String id;
    private String name;
    //
    // moving table columns/rows goes here
    //
    @OneToMany(mappedBy = "playlist")
    private List<Track> tracks;

    public Playlist() {} //constructor

    public Playlist(String id, String name, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.tracks = tracks;
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

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
