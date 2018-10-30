package com.example.weather.boundry.errorhandling;

import com.example.weather.boundry.exceptions.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException ex)
    {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<ErrorDto>(this.generateErrorDto(HttpStatus.BAD_REQUEST, ex), HttpStatus.BAD_REQUEST);
    }


    private ErrorDto generateErrorDto(HttpStatus status, Exception ex)
    {
        return new ErrorDto(status.value(), ex.getMessage());
    }
}
