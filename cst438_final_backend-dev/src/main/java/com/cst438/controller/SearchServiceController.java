package com.cst438.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cst438.domain.Track;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import javax.sound.midi.Track;


@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
//@CrossOrigin
public class SearchServiceController {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SearchServiceController.class);

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
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            logger.info("URL: {}", url);
            logger.info("Response from Spotify API: {}", response.getBody());

            // Process the response body to convert it into List<Track>
            // Assuming you have a method to convert JSON to List<Track>
            List<Track> tracks = convertJsonToTracks(response.getBody());
            return ResponseEntity.ok(tracks).getBody();

        } catch (Exception e) {
            logger.error("Error during Spotify API call", e);
            return (List<Track>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching data from Spotify");
        }


    }


    private List<Track> convertJsonToTracks(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<Track> tracks = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(json);
            JsonNode tracksNode = root.path("tracks").path("items");

            if (tracksNode.isArray()) {
                for (JsonNode node : tracksNode) {
                    Track track = mapper.treeToValue(node, Track.class);
                    tracks.add(track);
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing JSON", e);
        }

        return tracks;
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