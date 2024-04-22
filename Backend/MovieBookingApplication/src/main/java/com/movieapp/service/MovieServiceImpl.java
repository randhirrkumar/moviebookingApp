package com.movieapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.movieapp.exception.DuplicateMovieIdException;
import com.movieapp.exception.ResourceNotFoundException;
import com.movieapp.model.Movie;
import com.movieapp.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepo;
	
	@Override
	public Movie addMovie(Movie movie) throws DuplicateMovieIdException {
		Optional<Movie> movieObj=movieRepo.findById(movie.getMovieId());
		if(movieObj.isPresent()) {
			throw new DuplicateMovieIdException();
		}
		return movieRepo.saveAndFlush(movie);
	}

		@Override
		public List<Movie> getAllMovies() {
			List<Movie> movieList=movieRepo.findAll();
			if(movieList!=null && movieList.size()>0) {
				return movieList;
			}
			return null;
		}

	@Override
	public Movie getMovieById(int mId) {
		Optional<Movie> movie=movieRepo.findById(mId);
		if(movie.isPresent()) {
			return movie.get();
		}else {
			throw new ResourceNotFoundException("Movie", "movieId", mId);
		}
	}

	@Override
	public boolean updateMovie(Movie movie) {
		Movie existingMovie = movieRepo.findById(movie.getMovieId()).get();
		if(existingMovie!=null) {
			existingMovie.setMovieName(movie.getMovieName());
			existingMovie.setTheatreName(movie.getTheatreName());
			existingMovie.setTicketsAvailable(movie.getTicketsAvailable());
			existingMovie.setTicketsBooked(movie.getTicketsBooked());
			movieRepo.saveAndFlush(existingMovie);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean deleteMovie(int mId) {
		movieRepo.findById(mId).orElseThrow(() -> new ResourceNotFoundException("Movie", "movieId", mId));
		movieRepo.deleteById(mId);
		return true;
		
	}

}
