package com.gorygon.springboot.demoapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler({ResponseStatusException.class})
	public ResponseEntity<Object> handleAPIException(ResponseStatusException exception) {
		ExceptionRestResponse body = new ExceptionRestResponse(exception.getMessage(),
						exception.getStatus(), ZonedDateTime.now());
		return new ResponseEntity<>(body, exception.getStatus());
	}
}
