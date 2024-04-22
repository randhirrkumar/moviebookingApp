package com.movieapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.model.Movie;
import com.movieapp.model.Ticket;
import com.movieapp.service.MovieServiceImpl;
import com.movieapp.service.TicketServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketController {

	@Mock
	private TicketServiceImpl ticketService; 
	
	@Mock
	private MovieServiceImpl movieService;
	
	@InjectMocks
	private TicketController ticketController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(ticketController).build();
	}
	
	@Test
	public void bookTicketFailure() throws Exception {
		Ticket ticket = new Ticket();
		ticket.setMovieIdFk(101);
		ticket.setMovieName("Joker");
		ticket.setTheatreName("PVR");
		ticket.setNoOfTicket(10);
		
		when(ticketService.bookTicket(any())).thenReturn(null);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/ticket/bookTicket")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(ticket)))
	            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
}
