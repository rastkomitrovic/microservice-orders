package com.fon.konstrukcije.microservice.orders.config;

import com.fon.konstrukcije.microservice.orders.exception.NarudzbenicaMicroserviceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler(value = {NarudzbenicaMicroserviceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleNarudzbeniceMicroserviceException(NarudzbenicaMicroserviceException exception, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", "Greska u zahtevu");
        body.put("errors", exception.getMessage());

        logger.info("Greska u izvrsavanju " + body, exception);

        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("message", "Greska u parametrima API poziva");
        body.put("errors", errors);

        logger.info("Greska u parametrima API poziva " + body, exception);

        return handleExceptionInternal(exception, body, headers, HttpStatus.BAD_REQUEST, webRequest);
    }
}
