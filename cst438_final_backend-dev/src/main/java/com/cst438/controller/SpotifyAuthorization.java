package com.cst438.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class SpotifyAuthorization {

	static final String CLIENTID = "2dc223e330e94971a6dba250de16a4a6";
	static final String REDIRECTURL = "https://localhost:3000/"; // make sure this is the same in spotify

	    public SpotifyAuthorization() {
	        try {
	            String url_auth = 
	            "https://accounts.spotify.com/authorize?"
	            + "client_id="+CLIENTID+"&"
	            + "response_type=code&"
	            + "redirect_uri="+REDIRECTURL+"&"
	            + "scope=user-read-private%20user-read-email&"
	            + "state=34fFs29kd09";

	            System.out.println(url_auth);

	            URL url = new URL(url_auth);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", "application/json");

	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	            }

	            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

	            String output;
	            System.out.println("Output from Server .... \n");
	            while ((output = br.readLine()) != null) {
	                System.out.println(output);
	            }
	            conn.disconnect();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }

	}
