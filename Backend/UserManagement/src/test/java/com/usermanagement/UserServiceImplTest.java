package com.usermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepo; // Object to be injected
	
	@InjectMocks
	private UserServiceImpl userService; // in which @Mock object is injected
	
	@Autowired
	private MockMvc mockmvc; // because we do not have actual spring container
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(userService).build(); // autowiring and initialization
	}
	
	private List<User> userList = new ArrayList<>();
	
	
	@Test
	public void getAllUserSuccess() throws Exception{
		User user = new User();
		user.setId(101);
		user.setFullname("Charlie");
		user.setUsername("charlie");
		user.setEmail("charlie@gmail.com");
		user.setPhoneno(781782797);
		
		userList.add(user);
		
		when(userRepo.findAll()).thenReturn(userList);
		
	}
	
	@Test
	public void getAllUserFailure() throws Exception {
		when(userRepo.findAll()).thenReturn(null);
	}
	
	@Test
	public void addUserSuccess() throws Exception {
		User user = new User();
		user.setId(101);
		user.setFullname("Charlie");
		user.setUsername("charlie");
		user.setEmail("charlie@gmail.com");
		user.setPhoneno(781782797);
		
		userList.add(user);
		
		when(userRepo.save(any())).thenReturn(user);
	}
	   
	@Test
	public void addUserFailure() throws Exception {
		User user = new User();
		user.setId(101);
		user.setFullname("Charlie");
		user.setUsername("charlie");
		user.setEmail("charlie@gmail.com");
		user.setPhoneno(781782797);
		
		userList.add(user);
		
		when(userRepo.save(any())).thenReturn(null);
	}
	
	@Test 
	public void forgotPasswordSuccess() throws Exception {
		User user = new User();
		user.setUsername("testuser");
		user.setEmail("test@example.com");
		user.setQuestion("cat");
		user.setPassword("oldPassword");

		when(userRepo.findByUserName(eq("testuser"))).thenReturn(Optional.of(user));

		User result = userService.forgotPassword(user, "test@example.com", "cat", "newPassword");
		verify(userRepo, times(1)).save(eq(user));

		assertEquals("newPassword", result.getPassword());
	}
	
	@Test
	public void forgotPasswordFailure() throws Exception {
		User user = new User();
		user.setUsername("testuser");
		user.setEmail("test@example.com");
		user.setQuestion("cat");
		user.setPassword("oldPassword");
		
		when(userRepo.findByUserName(eq("testuser"))).thenReturn(Optional.of(user));
		
		try {
		    userService.forgotPassword(user, "wrong@example.com", "cat", "newPassword");
		    Assertions.fail("Expected exception to be thrown, but nothing was thrown.");
		} catch (Exception e) {
		    assertTrue(e instanceof Exception, "Unexpected exception type.");
		}
		
		verify(userRepo, never()).save(any());
    }

}
