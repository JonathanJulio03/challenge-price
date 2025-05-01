package com.challenge.price.commons.exception;

import static com.challenge.price.commons.helper.Constants.REQUEST_ERROR;

import com.challenge.price.commons.exception.message.TechnicalErrorMessage;
import com.challenge.price.domain.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(TechnicalException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleExceptions(TechnicalException ex) {
    log.error(ex);
    return new ResponseEntity<>(getErrorResponses(ex), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleExceptions(Exception ex) {
    log.error(ex);
    return new ResponseEntity<>(getErrorResponses(ex), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex) {
    log.error(ex);
    ErrorResponse errorResponse = ErrorResponse.builder()
        .code(REQUEST_ERROR)
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private static ErrorResponse getErrorResponses(TechnicalException technicalException) {
    return ErrorResponse.builder()
        .code(technicalException.getTechnicalErrorMessage().getCode())
        .message(technicalException.getTechnicalErrorMessage().getMessage())
        .build();
  }

  private static ErrorResponse getErrorResponses(Exception ex) {
    ErrorResponse errorResponse;
    try {
      throw ex;
    } catch (HttpRequestMethodNotSupportedException e) {
      errorResponse = ErrorResponse.builder()
          .code(TechnicalErrorMessage.SERVICE_NOT_FOUND.getCode())
          .message(TechnicalErrorMessage.SERVICE_NOT_FOUND.getMessage())
          .build();
    } catch (Exception e) {
      errorResponse = ErrorResponse.builder()
          .code(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getCode())
          .message(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getMessage())
          .build();
    }
    return errorResponse;
  }
}
