package com.igrowker.donatello.exceptions.MANEJO_PENDIENTE_BORRAR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<String> alreadyExistException (RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidValueException.class)
    @ResponseBody
    public ResponseEntity<String> invalidValue (RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(InvalidJwtException.class)
    @ResponseBody
    public ResponseEntity<String> invalidJwt (RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> notFound (RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_FOUND);
    }
}
