package com.cst438.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SpotifyControllerTest {

    @Mock
    private SpotifyService spotifyService;

    @InjectMocks
    private SpotifyController spotifyController;

    @Test
    void testGetUserProfile() {
        // Mock data
        String accessToken = "mockAccessToken";
        UserProfile mockUserProfile = new UserProfile();

        // Mock the service method
        when(spotifyService.getUserProfile(anyString())).thenReturn(mockUserProfile);

        // Call the controller method
        ResponseEntity<UserProfile> responseEntity = spotifyController.getUserProfile(accessToken);

        // Assert the response
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        
    }
}
