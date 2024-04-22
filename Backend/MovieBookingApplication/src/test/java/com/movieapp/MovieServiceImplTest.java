package com.movieapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.movieapp.repository.MovieRepository;
import com.movieapp.service.MovieServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieServiceImplTest {

	@Mock
	private MovieRepository movieRepo;
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@Autowired
	private MockMvc mockmvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(movieService).build();
	}
	
	private List<Movie> movieList = new ArrayList<>();
	
	@Test
	public void getAllMoviesSuccess() throws Exception{
		Movie movie = new Movie();
		movie.setMovieId(101);
		movie.setMovieName("Joker");
		movie.setTheatreName("PVR");
		movie.setTicketsAvailable(100);
		movie.setTicketsBooked(0);
		
		movieList.add(movie);
		
		when(movieRepo.findAll()).thenReturn(movieList);
		
	}
	
	@Test
	public void getAllMovieFailure() throws Exception {
		when(movieRepo.findAll()).thenReturn(null);
	}
	
	@Test
	public void addMovieSuccess() throws Exception {
		Movie movie = new Movie();
		movie.setMovieId(101);
		movie.setMovieName("Joker");
		movie.setTheatreName("PVR");
		movie.setTicketsAvailable(100);
		movie.setTicketsBooked(0);
		
		movieList.add(movie);
		
		when(movieRepo.save(movie)).thenReturn(movie);
	}
	
	@Test
	public void addMovieFailure() throws Exception {
		Movie movie = new Movie();
		movie.setMovieId(101);
		movie.setMovieName("Joker");
		movie.setTheatreName("PVR");
		movie.setTicketsAvailable(100);
		movie.setTicketsBooked(0);
		
		movieList.add(movie);
		
		when(movieRepo.save(movie)).thenReturn(null);
	}
	

	@Test
	public void updateMovieSuccess() throws Exception{
		Movie existingMovie = new Movie();
	    existingMovie.setMovieId(101);
	    existingMovie.setMovieName("Joker");
	    existingMovie.setTheatreName("PVR");
	    existingMovie.setTicketsAvailable(200);
	    existingMovie.setTicketsBooked(0);

	    when(movieRepo.findById(existingMovie.getMovieId())).thenReturn(Optional.of(existingMovie));
	    when(movieRepo.saveAndFlush(existingMovie)).thenReturn(existingMovie);	
	    
	    Movie updatedMovie = new Movie();
	    updatedMovie.setMovieId(existingMovie.getMovieId());
	    updatedMovie.setMovieName("Joker Returns");
	    updatedMovie.setTheatreName("PVR Cinemas");
	    updatedMovie.setTicketsAvailable(150);
	    updatedMovie.setTicketsBooked(50);
	    
	    when(movieRepo.findById(updatedMovie.getMovieId())).thenReturn(Optional.of(updatedMovie));
	    when(movieRepo.saveAndFlush(updatedMovie)).thenReturn(updatedMovie);
	}
	

	@Test
	public void updateMovieFailure() {
	    Movie movie = new Movie();
	    movie.setMovieId(101);
	    movie.setMovieName("Joker");
	    movie.setTheatreName("PVR");
	    movie.setTicketsAvailable(200);
	    movie.setTicketsBooked(0);
	    
	    when(movieRepo.findById(movie.getMovieId())).thenReturn(Optional.empty());
	    
	}
	
	@Test
	public void deleteMovieSuccess() {
		 Movie movie = new Movie();
		    movie.setMovieId(101);
		    movie.setMovieName("Joker");
		    movie.setTheatreName("PVR");
		    movie.setTicketsAvailable(200);
		    movie.setTicketsBooked(0);
		  
		    when(movieRepo.findById(movie.getMovieId())).thenReturn(Optional.of(new Movie()));
		    doNothing().when(movieRepo).deleteById(movie.getMovieId());
	}
	
	@Test
	public void deleteMovieFailure() {
		 Movie movie = new Movie();
		    movie.setMovieId(101);
		    movie.setMovieName("Joker");
		    movie.setTheatreName("PVR");
		    movie.setTicketsAvailable(200);
		    movie.setTicketsBooked(0);
		  
		    when(movieRepo.findById(movie.getMovieId())).thenReturn(Optional.empty());
	}
}
