package com.usermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.usermanagement.model.User;


public class UserTest {

	@Test
	public void test01() {
		User userObj = new User();
		
		userObj.setId(101);
		userObj.setFullname("Priya Malini");
		userObj.setUsername("priya");
		assertEquals(userObj.getUsername(),"priya");
		userObj.setEmail("priya@gmail.com");
		userObj.setPhoneno(7982681);
		userObj.getFullname();
		
		User usermock = Mockito.mock(User.class);
		
		when(usermock.getFullname()).thenReturn(null);
		
	}
	
	@Test
	public void test02() {
		User userObj = new User();
		
		userObj.setId(101);
		userObj.setFullname("Priya Malini");
		userObj.setUsername("priya");
		assertEquals(userObj.getUsername(),"priya");
		userObj.setEmail("priya@gmail.com");
		userObj.setPhoneno(7982681);
		userObj.getFullname();
		
		User usermock = Mockito.mock(User.class);
		
		when(usermock.getFullname()).thenReturn("Priya Malini");
	}
	
}
