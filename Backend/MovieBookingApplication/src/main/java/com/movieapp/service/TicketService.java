package com.movieapp.service;

import java.util.List;

import com.movieapp.model.Ticket;

public interface TicketService {
	Ticket bookTicket(Ticket ticket);
	
	List<Ticket> getTicketByMovieId(int movieId);
}
