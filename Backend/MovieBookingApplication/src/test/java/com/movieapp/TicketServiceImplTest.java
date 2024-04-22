package com.movieapp;

import static org.mockito.Mockito.mock;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.movieapp.model.Movie;
import com.movieapp.model.Ticket;
import com.movieapp.repository.MovieRepository;
import com.movieapp.repository.TicketRepository;
import com.movieapp.service.TicketService;
import com.movieapp.service.TicketServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class TicketServiceImplTest {

	@Mock
	private TicketServiceImpl ticketService; 
	
	@Mock
	private TicketRepository ticketRepo;
	
	@Mock
	private MovieRepository movieRepo;
	
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
    public void bookTicketSuccess() {
        Ticket ticket = new Ticket();
        ticket.setMovieName("Joker");
        ticket.setTheatreName("PVR");
        ticket.setNoOfTicket(10);
        
        Movie movie = new Movie();
        movie.setMovieName("Joker");
        movie.setTheatreName("PVR");
        movie.setTicketsAvailable(100);
        movie.setTicketsBooked(0);
        
        when(movieRepo.getByMovieNameAndTheatreName(ticket.getMovieName(), ticket.getTheatreName())).thenReturn(movie);
        when(ticketRepo.saveAndFlush(ticket)).thenReturn(ticket);
	}
	
	@Test
    public void bookTicketFailure() {
        Ticket ticket = new Ticket();
        ticket.setMovieName("Joker");
        ticket.setTheatreName("PVR");
        ticket.setNoOfTicket(10);
        
        Movie movie = new Movie();
        movie.setMovieName("Joker");
        movie.setTheatreName("PVR");
        movie.setTicketsAvailable(100);
        movie.setTicketsBooked(0);
        
        when(movieRepo.getByMovieNameAndTheatreName(ticket.getMovieName(), ticket.getTheatreName())).thenReturn(movie);
        when(ticketRepo.saveAndFlush(ticket)).thenReturn(null);
	}
	
	 @Test
	    public void bookTicketSoldOut() {
	        Ticket ticket = new Ticket();
	        ticket.setMovieName("Joker");
	        ticket.setTheatreName("PVR");
	        ticket.setNoOfTicket(10);
	        
	        Movie movie = new Movie();
	        movie.setMovieName("Joker");
	        movie.setTheatreName("PVR");
	        movie.setTicketsAvailable(5);
	        movie.setTicketsBooked(0);
	        
	        when(movieRepo.getByMovieNameAndTheatreName(ticket.getMovieName(), ticket.getTheatreName())).thenReturn(movie);
	        when(ticketRepo.saveAndFlush(ticket)).thenReturn(null);
	      
	 }
	 
	 	@Test
	    public void getTicketByMovieIdSuccess() {
	 		 Ticket ticket = new Ticket();
		        ticket.setMovieName("Joker");
		        ticket.setTheatreName("PVR");
		        ticket.setNoOfTicket(10);
		        
		        Movie movie = new Movie();
		        movie.setMovieName("Joker");
		        movie.setTheatreName("PVR");
		        movie.setTicketsAvailable(5);
		        movie.setTicketsBooked(0);
	        
	        List<Ticket> expectedTickets = new ArrayList<>();
	        expectedTickets.add(new Ticket());
	        expectedTickets.add(new Ticket());
	        
	        when(ticketRepo.getTicketByMovieId(movie.getMovieId())).thenReturn(expectedTickets);
	 }
	 	
	 	@Test
	    public void getTicketByMovieIdFailure() {
	 		 Ticket ticket = new Ticket();
		        ticket.setMovieName("Joker");
		        ticket.setTheatreName("PVR");
		        ticket.setNoOfTicket(10);
		        
		        Movie movie = new Movie();
		        movie.setMovieName("Joker");
		        movie.setTheatreName("PVR");
		        movie.setTicketsAvailable(5);
		        movie.setTicketsBooked(0);
	        
	        List<Ticket> expectedTickets = new ArrayList<>();
	        expectedTickets.add(new Ticket());
	        expectedTickets.add(new Ticket());
	        
	        when(ticketRepo.getTicketByMovieId(movie.getMovieId())).thenReturn(null);
	 }
}
