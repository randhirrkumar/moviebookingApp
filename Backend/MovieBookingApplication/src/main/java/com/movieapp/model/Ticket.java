package com.movieapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	private int transactionId;
	
	public Ticket() {
		super();
		
	}

	public Ticket(int transactionId, String movieName, String theatreName, int noOfTicket, int movieIdFk) {
		super();
		this.transactionId = transactionId;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.noOfTicket = noOfTicket;
		this.movieIdFk = movieIdFk;
	}

	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="theatre_name")
	private String theatreName;
	
	@Column(name="no_of_ticket")
	private int noOfTicket;
	
	@Column(name="movie_id_fk")
	private int movieIdFk;

	
	public int getMovieIdFk() {
		return movieIdFk;
	}

	public void setMovieIdFk(int movieIdFk) {
		this.movieIdFk = movieIdFk;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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

	public int getNoOfTicket() {
		return noOfTicket;
	}

	public void setNoOfTicket(int noOfTicket) {
		this.noOfTicket = noOfTicket;
	}
}
