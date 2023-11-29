package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping("/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    @Autowired
    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/user-profile")
    public ResponseEntity<UserProfile> getUserProfile(@RequestParam String accessToken) {
        UserProfile userProfile = spotifyService.getUserProfile(accessToken);
        return ResponseEntity.ok(userProfile);
    }
}
