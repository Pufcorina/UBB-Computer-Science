package ro.ubb.lab7.web.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by radu.
 * <p>
 * http://www.baeldung.com/exception-handling-for-rest-with-spring
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        log.trace("handleConflict: ex={}", ex);

        try {
            if (ex.getCause().getCause() instanceof ConstraintViolationException) {
                return handleConstraintViolation(
                        (ConstraintViolationException) (ex.getCause().getCause()),
                        request);
            }
        } catch (Exception e) {
            log.trace("handleConflict - exception occurred (expecting NPE or CCE) - IGNORE: e={}", e);
        }

        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        log.trace("handleConstraintViolation: ex={}", ex);

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.trace("handleMethodArgumentNotValid: ex={}", ex);

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST,
                        ex.getLocalizedMessage(),
                        ex.getBindingResult().toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}

@Getter
class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    ApiError(HttpStatus status, String message, String error) {
        this(status, message, Arrays.asList(error));
    }
}
