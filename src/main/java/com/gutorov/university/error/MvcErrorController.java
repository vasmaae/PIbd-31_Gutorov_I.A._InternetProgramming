package com.gutorov.university.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Profile("!front")
@ControllerAdvice
public class MvcErrorController {
    private HttpStatus getStatus(HttpServletRequest request) {
        final Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        final HttpStatus status = (code != null) ? HttpStatus.resolve(code) : null;
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Throwable throwable) {
        final HttpStatus status = getStatus(request);
        final ModelAndView model = new ModelAndView();
        model.addObject("details", new AdviceErrorBody(status.value(), throwable.getMessage()));
        model.setViewName("error");
        return model;
    }
}
