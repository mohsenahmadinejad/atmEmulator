package com.mohsen.bankservice.exception.handler;

import com.mohsen.bankservice.exception.*;
import com.mohsen.common.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.time.format.DateTimeParseException;


@ControllerAdvice
@ResponseStatus
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UntAuthorizedException.class)
    public ResponseEntity<ExceptionDto> unAuthorizedExceptionHandler(UntAuthorizedException exception, WebRequest request){
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.UNAUTHORIZED,exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDto);
    }
    @ExceptionHandler(CardIsBlockedException.class)
    public ResponseEntity<ExceptionDto> cardIsBlockedExceptionHandler(CardIsBlockedException exception, WebRequest request){
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.FORBIDDEN,exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDto);
    }
    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<ExceptionDto> notEnoughBalanceExceptionHandler(NotEnoughBalanceException exception, WebRequest request){
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.BAD_REQUEST,exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ExceptionDto> cardNotFoundExceptionHandler(CardNotFoundException exception, WebRequest request){
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.NOT_FOUND,exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }
    @ExceptionHandler(NotPositiveAmountException.class)
    public ResponseEntity<ExceptionDto> cardNotPositiveAmountExceptionHandler(NotPositiveAmountException exception, WebRequest request){
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.BAD_REQUEST,exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler({DateTimeParseException.class,
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class,
            BindException.class})
    public ResponseEntity<ExceptionDto> inputParameterExceptionHandler(Exception exception, WebRequest request) {
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR,
                "Malformed syntax of the request params");
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> generalExceptionHandler(Exception exception, WebRequest request) {
        log.error(exception.getMessage());
        ExceptionDto exceptionDto=new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }




}
