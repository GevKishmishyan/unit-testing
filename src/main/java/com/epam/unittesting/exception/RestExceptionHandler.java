package com.epam.unittesting.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Handles EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(EntityNotFoundException ex) {
        String localizedMessage = ex.getLocalizedMessage();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(localizedMessage);

        return errorResponse;
    }

    /**
     * Handles EntityAlreadyExistException.
     *
     * @param ex the EntityAlreadyExistException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorResponse handleAlreadyExistException(EntityAlreadyExistException ex) {
        String localizedMessage = ex.getLocalizedMessage();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(localizedMessage);

        return errorResponse;
    }

}
