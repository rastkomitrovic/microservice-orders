package com.fon.konstrukcije.microservice.orders.config;

import com.fon.konstrukcije.microservice.orders.exception.NarudzbeniceMicroserviceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NarudzbeniceMicroserviceException.class})
    protected ResponseEntity<Object> handleNarudzbeniceMicroserviceException(NarudzbeniceMicroserviceException exception, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Greska u zahtevu");
        body.put("greske", exception.getMessage());
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
