package com.cst438.controller;

import com.cst438.controller.SpotifyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SpotifyService spotifyService;

    @InjectMocks
    private UserProfileController userProfileController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController)
                .build();
    }

    @Test
    public void testGetUserProfile() throws Exception {
        // Mock data
        String accessToken = "testAccessToken";
        UserProfile mockUserProfile = new UserProfile(/* Set your mock data here */);

        // Mock SpotifyService response
        when(spotifyService.getUserProfile(accessToken)).thenReturn(mockUserProfile);

        // Perform GET request
        mockMvc.perform(get("/api/user/profile")
                .param("accessToken", accessToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify that the SpotifyService method was called with the correct arguments
        verify(spotifyService, times(1)).getUserProfile(accessToken);
        verifyNoMoreInteractions(spotifyService);
    }
}
