package com.movieapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieapp.model.Movie;
import com.movieapp.model.Ticket;
import com.movieapp.repository.MovieRepository;
import com.movieapp.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private TicketRepository ticketRepo;
	
	@Override
	public Ticket bookTicket(Ticket ticket) {
		Movie movie = movieRepo.getByMovieNameAndTheatreName(ticket.getMovieName(), ticket.getTheatreName());
		
		if(movie.getTicketsAvailable() < ticket.getNoOfTicket()) {
			throw new IllegalArgumentException("SOLD OUT");
		}
		
		
		int bookedTickets = movie.getTicketsBooked() + ticket.getNoOfTicket();
		int availableTickets = movie.getTicketsAvailable() - ticket.getNoOfTicket();
		
		movie.setTicketsBooked(bookedTickets);
		movie.setTicketsAvailable(availableTickets);
		
		return ticketRepo.saveAndFlush(ticket);
	}


	@Override
	public List<Ticket> getTicketByMovieId(int movieId) {
		return ticketRepo.getTicketByMovieId(movieId);
	}

}
