package com.movieapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Movie id already exist.")
public class DuplicateMovieIdException extends Exception{
	
	private static final long serialVersionUID = 1L;

}
