package com.movieapp;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.movieapp.model.Movie;

public class MovieTest {

	@Test
	public void test01() {
		Movie movieObj = new Movie();
		
		movieObj.setMovieId(101);
		movieObj.setMovieName("Joker");
		movieObj.setTheatreName("PVR");
		movieObj.setTicketsAvailable(100);
		movieObj.setTicketsBooked(0);
		
		Movie moviemock = Mockito.mock(Movie.class);
		
		when(moviemock.getMovieName()).thenReturn(null);
		
	}
	

	@Test
	public void test02() {
		Movie movieObj = new Movie();
		
		movieObj.setMovieId(101);
		movieObj.setMovieName("Joker");
		movieObj.setTheatreName("PVR");
		movieObj.setTicketsAvailable(100);
		movieObj.setTicketsBooked(0);
		
		Movie moviemock = Mockito.mock(Movie.class);
		
		when(moviemock.getMovieName()).thenReturn("Joker");
		
	}
}
