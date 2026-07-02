package com.faros.gestionusuarios.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarNoEncontrado(

            ResourceNotFoundException ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.NOT_FOUND.value(),

                "NOT FOUND",

                ex.getMessage(),

                request.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidaciones(

            MethodArgumentNotValidException ex,

            HttpServletRequest request) {

        String mensaje = ex.getBindingResult()

                .getFieldErrors()

                .stream()

                .map(FieldError::getDefaultMessage)

                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.BAD_REQUEST.value(),

                "BAD REQUEST",

                mensaje,

                request.getRequestURI()

        );

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> manejarConstraint(

            ConstraintViolationException ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.BAD_REQUEST.value(),

                "BAD REQUEST",

                ex.getMessage(),

                request.getRequestURI()

        );

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGeneral(

            Exception ex,

            HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.INTERNAL_SERVER_ERROR.value(),

                "INTERNAL SERVER ERROR",

                ex.getMessage(),

                request.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

    }

}