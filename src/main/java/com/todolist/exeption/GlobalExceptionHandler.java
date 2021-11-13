package com.todolist.exeption;

import com.todolist.model.ItemResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

import static com.todolist.util.AppConstants.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * This method handles exception errors ,that can be an Internal Server Error
     * when something unexpected has occurred.
     **/
    @ExceptionHandler(ItemNoFoundExeption.class)
    public ResponseEntity<Object> springHandleNotFound(ItemNoFoundExeption itemNoFoundExeption) {
        return buildErrorResponse(itemNoFoundExeption.getMessage(), HttpStatus.OK, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {
        log.error(INTERNAL_SERVER_ERROR + "exception : {}  url : {}", exception, ((ServletWebRequest) request).getRequest().getRequestURI());
        return buildErrorResponse(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleExdceptions(ConstraintViolationException constraintViolationException, WebRequest request) {
        log.error(BAD_REQUEST + "constraintViolationException : {}  url : {}", constraintViolationException, ((ServletWebRequest) request).getRequest().getRequestURI());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", BAD_REQUEST.value());

        List<String> errors = constraintViolationException.getConstraintViolations().stream()
                .map(x -> x.getPropertyPath() + " " + x.getMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);

        return buildErrorResponse(BAD_REQUEST.toString(), BAD_REQUEST, errors);
    }

    private ResponseEntity<Object> buildErrorResponse(String message,
                                                      HttpStatus status,
                                                      List<String> errors) {
        return ResponseEntity.status(status).body(new ItemResponse(false, message, errors, null));
    }
}
