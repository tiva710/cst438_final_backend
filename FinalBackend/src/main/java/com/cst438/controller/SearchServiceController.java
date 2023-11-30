package com.cst438.controller;

import com.cst438.domain.Track;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//import javax.sound.midi.Track;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
//@CrossOrigin
public class SearchServiceController {

    private final RestTemplate restTemplate;

    public SearchServiceController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/api/test")
    public String testEndpoint() {
        return "Test successful";
    }

//    @GetMapping("/api/search/{query}")
//    public String searchTracks(@PathVariable String query) {
//        return "Query received: " + query;
//    }

    @GetMapping("/api/search/{query}")
    public List<Track> searchTracks(@PathVariable String query, @RequestHeader("Authorization") String accessToken) {
        System.out.println("Received headers: Authorization - " + accessToken);

//        String url = "https://api.spotify.com/v1/search?type=genres&q=" + query;
        String url = "https://api.spotify.com/v1/search?q=" + query + "&type=track"; // Or artist, album
//          String url = "https://api.spotify.com/v1/search?q={query}&type=track,artist,album";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken); // Adding 'Bearer ' prefix
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Track[]> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, Track[].class);
            return response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (Exception e) {
            // Log the error and return an appropriate response or an empty list
            return List.of();
        }

    }

//    @Override
//    @CrossOrigin
//    public List<Track> searchTracks(String query, String accessToken) {
//        String url = "https://api.spotify.com/v1/search?type=genres&q=" + query; // Corrected URL
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + accessToken);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Track[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Track[].class);
//        return Arrays.asList(Objects.requireNonNull(response.getBody()));
//    }

}