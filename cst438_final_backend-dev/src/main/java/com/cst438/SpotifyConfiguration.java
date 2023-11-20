package com.cst438;

import java.net.URI;

import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager; 

@Service
public class SpotifyConfiguration {
	
	private String customIp = "https://localhost:8080";
	
	public SpotifyApi getSpotifyObject() {
		URI redirectURL = SpotifyHttpManager.makeUri(customIp + "/api/get-user-code/");
		
		return new SpotifyApi
				.Builder()
				.setClientId("2dc223e330e94971a6dba250de16a4a6")
				.setClientSecret("15fcb0ce352b4a1d9ac5c1964737ba72")
				.setRedirectUri(redirectURL)
				.build();
		
	}
}
