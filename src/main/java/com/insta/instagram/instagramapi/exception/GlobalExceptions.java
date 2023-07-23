package com.insta.instagram.instagramapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorDetails> CommentExceptionHandler(CommentException ce, WebRequest req ){
        ErrorDetails err = new ErrorDetails(ce.getMessage(), req.getDescription(false), LocalDateTime.now());

        return  new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException ue, WebRequest req){

        ErrorDetails err = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());

        return  new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorDetails> PostExceptionHandler(PostException pe, WebRequest req){

        ErrorDetails err = new ErrorDetails(pe.getMessage(), req.getDescription(false), LocalDateTime.now());

        return  new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> MethodArgumentExceptionHandler(MethodArgumentNotValidException me, WebRequest req){

        ErrorDetails err = new ErrorDetails(me.getBindingResult().getFieldError().getDefaultMessage(), "Validation Error", LocalDateTime.now());

        return  new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> OtherExceptionHandler(Exception e, WebRequest req){

        ErrorDetails err = new ErrorDetails(e.getMessage(), req.getDescription(false), LocalDateTime.now());

        return  new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

}
