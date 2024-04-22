package com.movieapp.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.movieapp.model.Ticket;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	@Query("SELECT t FROM Ticket t WHERE t.movieIdFk = ?1")
	public List<Ticket> getTicketByMovieId(@Param("movieId") int movieId);
}
