package com.movieapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.movieapp.model.LoginRequest;
import com.movieapp.service.JwtAuthenticationService;

@RestController
@CrossOrigin("*")
public class ConsumerController {
	
	@Autowired
	private JwtAuthenticationService jwtAuthenticationService;
	
	@PostMapping(value="/login")
	public ResponseEntity<String> performLogin(@RequestBody LoginRequest userdto) throws RestClientException,Exception {
			String token = jwtAuthenticationService.authenticate(userdto.getUsername(), userdto.getPassword());
		return new ResponseEntity<String>(token,HttpStatus.OK);
	}
}
