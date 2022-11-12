package com.mohsen.atmservice.exception;

import com.mohsen.common.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@ResponseStatus
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<ExceptionDto> notNotAuthenticatedExceptionHandler(NotAuthenticatedException exception, WebRequest request){
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.FORBIDDEN,exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDto);
    }

}
