package com.cst438.controller;

import com.cst438.controller.SearchServiceController;
import com.cst438.domain.Track;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchServiceControllerTest {

    @MockBean
    private SearchServiceController searchServiceController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTestEndpoint() throws Exception {
        mockMvc.perform(get("/api/test"))
               .andExpect(status().is(401));
    }

    @Test
    public void testSearchTracks() throws Exception {
        // Mock data
        String query = "test";
        String accessToken = "testAccessToken";
        List<Track> mockTracks = Arrays.asList(new Track(), new Track());

        // Mocking behavior
        Mockito.when(searchServiceController.searchTracks(eq(query), eq(accessToken)))
               .thenReturn(mockTracks);

        // Perform the request and assert the response
        mockMvc.perform(get("/api/search/{query}", query)
                .header("Authorization", accessToken))
               .andExpect(status().isOk());
    }

    @Test
    public void testSearchTracksWithError() throws Exception {
        // Mock data
        String query = "test";
        String accessToken = "testAccessToken";

        // Mocking behavior
        Mockito.when(searchServiceController.searchTracks(eq(query), eq(accessToken)))
               .thenReturn(null);

        // Perform the request and assert the response
        mockMvc.perform(get("/api/search/{query}", query)
                .header("Authorization", accessToken))
               .andExpect(status().is(200));
    }
}
