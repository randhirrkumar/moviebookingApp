package com.movieapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@Column(name="movie_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int movieId;
	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="theatre_name")
	private String theatreName;
	
	@Column(name="tickets_available")
	private int ticketsAvailable;
	
	@Column(name="tickets_booked")
	private int ticketsBooked;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="movie_id_fk", referencedColumnName="movie_id")
	private List<Ticket> ticket = new ArrayList<>();

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	public Movie() {
		super();
		
	}

	public Movie(int movieId, String movieName, String theatreName, int ticketsAvailable, int ticketsBooked,
			List<Ticket> ticket) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.ticketsAvailable = ticketsAvailable;
		this.ticketsBooked = ticketsBooked;
		this.ticket = ticket;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getTicketsAvailable() {
		return ticketsAvailable;
	}

	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}

	public int getTicketsBooked() {
		return ticketsBooked;
	}

	public void setTicketsBooked(int ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}
}
