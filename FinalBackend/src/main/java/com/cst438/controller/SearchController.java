//package com.cst438.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.sound.midi.Track;
//import java.util.List;
//@CrossOrigin
//@RestController
//@RequestMapping("/api/search")
//public class SearchController {
//
//    private final SearchService searchService;
//
//    @Autowired
//    public SearchController(SearchService searchService) {
//        this.searchService = searchService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Track>> searchTracks(@RequestParam String query, @RequestParam String accessToken) {
//        List<Track> tracks = searchService.searchTracks(query, accessToken);
//        return ResponseEntity.ok(tracks);
//    }
//
//}
