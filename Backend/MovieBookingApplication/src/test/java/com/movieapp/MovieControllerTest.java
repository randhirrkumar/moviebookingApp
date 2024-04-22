package com.movieapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.controller.MovieController;
//import com.movieapp.kafka.KafkaProducer;
import com.movieapp.model.Movie;
import com.movieapp.service.MovieServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

		@Mock
		private MovieServiceImpl movieService; 
		
		@InjectMocks
		private MovieController movieController;
		
//		@Mock
//		private KafkaProducer kafkaProducer;
		
		@Autowired
		private MockMvc mockmvc;
		
		@BeforeEach
		public void init() {
			MockitoAnnotations.openMocks(this);
			mockmvc = MockMvcBuilders.standaloneSetup(movieController).build();
		}
		
		List<Movie> movieList = new ArrayList<>();
		
		@Test
		public void addMovieSuccess() throws Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
		
//			Mockito.doNothing().when(kafkaProducer).sendMessage(Mockito.anyString());
			
			when(movieService.addMovie(any())).thenReturn(movie);
			
			mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/movie/addMovie")
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		}
		
		@Test
		public void addMovieFailure() throws Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
		
			
			when(movieService.addMovie(movie)).thenReturn(null);
			
			mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/movie/addMovie")
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		}

		@Test
		public void getMoviesSuccess() throws Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
			
			movieList.add(movie);
			
//			Mockito.doNothing().when(kafkaProducer).sendMessage(Mockito.anyString());
		
			when(movieService.getAllMovies()).thenReturn(movieList);
			
			mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/movie/all")
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
		
		@Test
		public void getMoviesFailure() throws Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
			
			movieList.add(movie);
		
			when(movieService.getAllMovies()).thenReturn(null);
			
			mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/movie/all")
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
		
		@Test
		public void updateMovieSuccess() throws JsonProcessingException, Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
		
//			Mockito.doNothing().when(kafkaProducer).sendMessage(Mockito.anyString());
			when(movieService.updateMovie(any())).thenReturn(true);
			
			mockmvc.perform(MockMvcRequestBuilders.put("/api/v1.0/movie/updateMovie")
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
		
		@Test
		public void updateMovieFailure() throws JsonProcessingException, Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
		
			when(movieService.updateMovie(movie)).thenReturn(false);
			
			mockmvc.perform(MockMvcRequestBuilders.put("/api/v1.0/movie/updateMovie")
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is5xxServerError());
		}
		
		@Test
		public void deleteMovieSuccess() throws JsonProcessingException, Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
			
			when(movieService.deleteMovie(movie.getMovieId())).thenReturn(true);
			
			mockmvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/movie/delete/{id}", movie.getMovieId())
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
		
		@Test
		public void deleteMovieFailure() throws JsonProcessingException, Exception {
			Movie movie = new Movie();
			movie.setMovieId(101);
			movie.setMovieName("Joker");
			movie.setTheatreName("PVR");
			movie.setTicketsAvailable(100);
			movie.setTicketsBooked(0);
			
			when(movieService.deleteMovie(movie.getMovieId())).thenReturn(false);
			
			mockmvc.perform(MockMvcRequestBuilders.delete("/api/v1.0/movie/delete/{id}", movie.getMovieId())
					.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(movie)))
					.andExpect(MockMvcResultMatchers.status().is5xxServerError());
		}
		
}
