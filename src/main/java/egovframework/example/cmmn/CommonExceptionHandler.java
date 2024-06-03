package egovframework.example.cmmn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> errorAll(final Exception ex) {
		log.debug("error", ex);
		
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
}
