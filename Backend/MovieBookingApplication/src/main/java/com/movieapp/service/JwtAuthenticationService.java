package com.movieapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movieapp.model.JwtAuthenticationResponse;
import com.movieapp.model.LoginRequest;
//import com.movieapp.model.MyUserDetails;
import com.movieapp.model.MyUserDetails;

@Service
public class JwtAuthenticationService {

	private final RestTemplate restTemplate;
	private final String authenticationProviderBaseUrl;

	public JwtAuthenticationService(RestTemplate restTemplate,
			@Value("${authentication.provider.baseurl}") String authenticationProviderBaseUrl) {
		this.restTemplate = restTemplate;
		this.authenticationProviderBaseUrl = authenticationProviderBaseUrl;
	}
	
    public String authenticate(String username, String password) {
        String authenticationUrl = authenticationProviderBaseUrl + "/login";
        
        LoginRequest loginRequest = new LoginRequest(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<LoginRequest>(loginRequest, headers);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate.exchange(
                authenticationUrl,  
                HttpMethod.POST,
                requestEntity,
                JwtAuthenticationResponse.class
        );

        JwtAuthenticationResponse authenticationResponse = responseEntity.getBody();
        if (authenticationResponse != null) {
            return authenticationResponse.getJwtToken();
        }
        
        return null;
    }
    
    public MyUserDetails validateToken(String token) {
    	String validationUrl = authenticationProviderBaseUrl + "/validateToken";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> requestEntity = new HttpEntity<>(token, headers);
        
        ResponseEntity<MyUserDetails> responseEntity = restTemplate.exchange(
        		validationUrl,
                HttpMethod.POST,
                requestEntity,
                MyUserDetails.class
        );
        System.out.println(responseEntity.getBody().getAuthorities());
        MyUserDetails result = responseEntity.getBody();
        System.out.println(result);
        return result;
    }
}
