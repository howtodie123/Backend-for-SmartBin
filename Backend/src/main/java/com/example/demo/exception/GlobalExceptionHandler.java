package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(
                        ApiResponse.builder()
                                .status(String.valueOf(ResponseStatus.FAIL))
                                .message("Registration Failed: Please provide valid data.")
                                .response(errorMessage)
                                .build()
                );
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiResponse.builder()
                                .status(String.valueOf(ResponseStatus.FAIL))
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> RoleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.builder()
                                .status(String.valueOf(ResponseStatus.FAIL))
                                .message(exception.getMessage())
                                .build()
                );
    }
}
