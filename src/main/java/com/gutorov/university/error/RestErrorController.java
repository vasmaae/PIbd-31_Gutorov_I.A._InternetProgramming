package com.gutorov.university.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Profile("front")
@RestControllerAdvice
public class RestErrorController {
    private HttpStatus getStatus(HttpServletRequest request) {
        final Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        final HttpStatus status = (code != null) ? HttpStatus.resolve(code) : null;
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<AdviceErrorBody> handleAnyException(HttpServletRequest request, Throwable ex) {
        final HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new AdviceErrorBody(status.value(), ex.getMessage()), status);
    }
}
