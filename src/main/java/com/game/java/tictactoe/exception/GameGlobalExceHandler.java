package com.game.java.tictactoe.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.game.java.tictactoe.entity.CustomErrorResponse;

@ControllerAdvice
public class GameGlobalExceHandler extends ResponseEntityExceptionHandler {
	

    @ExceptionHandler(GameInterruptedException.class)
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}
