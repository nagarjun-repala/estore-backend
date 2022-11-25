package com.nagarjun.estorebackend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.nagarjun.estorebackend.exception.ErrorResponse;
import com.nagarjun.estorebackend.exception.OrderNotFoundException;
import com.nagarjun.estorebackend.exception.ProductNotFoundException;
import com.nagarjun.estorebackend.exception.UserNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({UserNotFoundException.class, OrderNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException exception) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(exception.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
