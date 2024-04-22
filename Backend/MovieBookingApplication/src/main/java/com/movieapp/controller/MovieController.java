package com.movieapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.customResponse.ResponseHandler;
import com.movieapp.exception.DuplicateMovieIdException;
//import com.movieapp.kafka.KafkaProducer;
import com.movieapp.model.Movie;
import com.movieapp.service.MovieService;

@RestController
@RequestMapping("api/v1.0/movie")
@CrossOrigin("*")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
//	@Autowired
//	private KafkaProducer kafkaProducer;
	
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) throws DuplicateMovieIdException{
		if(movieService.addMovie(movie)!=null) {
//			kafkaProducer.sendMessage("Movie added successfully!!");
			return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Movie is not created in DB", HttpStatus.CONFLICT);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllMovies(){
		List<Movie> movieList = movieService.getAllMovies();
		if(movieList!=null) {
//			kafkaProducer.sendMessage("Movie list fetched successfully!!");
			return ResponseEntity.ok()
					.body(ResponseHandler.generateResponse("Movelist fetched successfully!!", HttpStatus.OK, movieList));
		}
		return new ResponseEntity<String>("Movielist is empty", HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/get/{mId}")
	public ResponseEntity<?> getMovieById(@PathVariable("mId") int mId){
		return new ResponseEntity<Movie>(movieService.getMovieById(mId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	@PutMapping("/updateMovie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
		if(movieService.updateMovie(movie)) {
//			kafkaProducer.sendMessage("Movie updated successfully!!");
			return new ResponseEntity<String>("Movie record updated", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Movie record not updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	@DeleteMapping("/delete/{mId}")
	public ResponseEntity<?> deleteMovie(@PathVariable("mId") int mId){
		Map<String,String> res = new HashMap<>();
		res.put("Status", "success");
		if(movieService.deleteMovie(mId)) {
			return new ResponseEntity<Map<String,String>>(res, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie record not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
