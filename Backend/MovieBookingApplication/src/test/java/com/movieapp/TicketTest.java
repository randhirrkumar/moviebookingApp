package com.movieapp;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.movieapp.model.Ticket;

public class TicketTest {

	@Test
	public void test01() {
		Ticket ticketObj = new Ticket();
		ticketObj.setMovieIdFk(101);
		ticketObj.setMovieName("Joker");
		ticketObj.setTheatreName("PVR");
		ticketObj.setNoOfTicket(10);
		
		Ticket ticketmock = Mockito.mock(Ticket.class);
		
		when(ticketmock.getMovieName()).thenReturn(null);
		
	}
	
	@Test
	public void test02() {
		Ticket ticketObj = new Ticket();
		ticketObj.setMovieIdFk(101);
		ticketObj.setMovieName("Joker");
		ticketObj.setTheatreName("PVR");
		ticketObj.setNoOfTicket(10);
		
		Ticket ticketmock = Mockito.mock(Ticket.class);
		
		when(ticketmock.getMovieName()).thenReturn("Joker");
		
	}
}
