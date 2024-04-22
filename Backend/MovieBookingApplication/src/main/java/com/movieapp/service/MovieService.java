	package com.movieapp.service;

import java.util.List;

import com.movieapp.exception.DuplicateMovieIdException;
import com.movieapp.model.Movie;

public interface MovieService {
	Movie addMovie(Movie movie) throws DuplicateMovieIdException;
	
	List<Movie> getAllMovies();
	
	Movie getMovieById(int mId);
	
	public boolean updateMovie(Movie movie);
	
	boolean deleteMovie(int mId);
	
}
