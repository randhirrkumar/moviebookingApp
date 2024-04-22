package com.movieapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.model.Movie;
import com.movieapp.model.Ticket;
import com.movieapp.service.MovieService;
import com.movieapp.service.TicketService;

@RestController
@RequestMapping("api/v1.0/ticket")
@CrossOrigin("*")
public class TicketController {
	
	@Autowired
	private MovieService movieService;

	@Autowired
	private TicketService ticketService;
	
	@PreAuthorize("hasAuthority('NORMAL_USER')")
	@PostMapping("/bookTicket")
	public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket){
		Movie existingMovie = movieService.getMovieById(ticket.getMovieIdFk());
		
		if(existingMovie != null) {
			ticket.setMovieIdFk(ticket.getMovieIdFk());
			ticket.setNoOfTicket(ticket.getNoOfTicket());
			ticket.setMovieName(existingMovie.getMovieName());
			ticket.setTheatreName(existingMovie.getTheatreName());
			
			return new ResponseEntity<>(ticketService.bookTicket(ticket), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("hasAuthority('NORMAL_USER')")
	@GetMapping("/get/{movieId}")
	public List<Ticket> getTicketByMovieId(@PathVariable() int movieId){
		
		return ticketService.getTicketByMovieId(movieId);
	
	}
}
