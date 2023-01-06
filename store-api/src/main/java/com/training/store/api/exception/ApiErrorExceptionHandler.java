package com.training.store.api.exception;

import com.training.store.commons.exceptions.GenericException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.nio.file.AccessDeniedException;
import java.util.Set;

/**
 * Intercept the exceptions accross the whole application and return custom error response.
 */
@ControllerAdvice
public class ApiErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleAppException(GenericException exception) {

        logger.error(exception.getMessage());
        ApiError error = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(exception.getMessage())
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        logger.error("Missing parameter: " + exception.getMessage());

        ApiError error = ApiError.builder()

            .status(HttpStatus.BAD_REQUEST)
            .message(exception.getParameterName() + " is missing.")
            .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Method argument type mismatch: " + exception.getMessage());
        ApiError error = ApiError.builder()
            .message("tototototototototo")
            .status(HttpStatus.BAD_REQUEST)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        logger.error("Method argument type mismatch: " + exception.getMessage());
        ApiError error = ApiError.builder()
            .message(String.format("Incorrect type for the property [%s] using the value [%s].",
                exception.getName(),
                exception.getValue()))

            .status(HttpStatus.BAD_REQUEST)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ApiError> handleMethodNotAllowedException(MethodNotAllowedException exception) {
        logger.error("Method argument type mismatch: " + exception.getMessage());
        ApiError error = ApiError.builder()
            .message("Methode non autoriséee")

            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException exception) {
        logger.error(exception.getMessage(), exception);
        ApiError error = ApiError.builder()
            .message("An technical error occurred during the operation.")

            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        StringBuilder constraintViolationMessage = new StringBuilder();
        constraintViolations.stream().iterator().forEachRemaining(constraintViolation -> constraintViolationMessage.append(constraintViolation.getMessage()));

        logger.error("Constraint(s) violated: " + constraintViolationMessage);

        ApiError error = ApiError.builder()
            .message(constraintViolationMessage.toString())

            .status(HttpStatus.BAD_REQUEST)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleAccessDeniedException() {
        logger.error("The user is not allowed to perform this operation.");
        ApiError error = ApiError.builder()
            .message("You are not authorized to perform this operation.")

            .status(HttpStatus.UNAUTHORIZED)
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

}
