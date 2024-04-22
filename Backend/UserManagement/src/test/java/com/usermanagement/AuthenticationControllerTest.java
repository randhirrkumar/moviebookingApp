package com.usermanagement;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.controller.AuthenticationController;
import com.usermanagement.model.User;
import com.usermanagement.service.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {


	@Mock
	private UserServiceImpl userService; 
    
    @Mock
    private UserDetailsService userDetailsService;
	
	@InjectMocks
	private AuthenticationController authController;
	
	@Autowired
	private MockMvc mockmvc;
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(authController).build();
	}
	
	@Test
	public void addUserSuccess() throws Exception {
		User user = new User();
		user.setId(101);
		user.setFullname("Charlie");
		user.setUsername("charlie");
		user.setEmail("charlie@gmail.com");
		user.setPhoneno(781782797);
	
		
		when(userService.registerUser(any())).thenReturn(user);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/auth/v1.0/register")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void addUserFailure() throws Exception {
		User user = new User();
		user.setId(101);
		user.setFullname("Charlie");
		user.setUsername("charlie");
		user.setEmail("charlie@gmail.com");
		user.setPhoneno(781782797);
	
		
		when(userService.registerUser(any())).thenReturn(null);
		
		User u1 = userService.registerUser(user);
		assertNull(u1);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/auth/v1.0/register")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(u1)))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	

}
