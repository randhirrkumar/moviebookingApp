package com.movieapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movieapp.model.Movie;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	@Query("SELECT m FROM Movie m WHERE m.movieName = ?1 and m.theatreName = ?2")
	public Movie getByMovieNameAndTheatreName(String movieName, String theatreName);
}
