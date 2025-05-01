package com.challenge.price.commons.exception;

import static com.challenge.price.commons.helper.Constants.REQUEST_ERROR;
import static org.junit.jupiter.api.Assertions.*;

import com.challenge.price.commons.exception.message.TechnicalErrorMessage;
import com.challenge.price.domain.ErrorResponse;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

  @InjectMocks
  private GlobalExceptionHandler exceptionHandler;

  @Test
  @DisplayName("Test method handle technical exception")
  void shouldHandleTechnicalException() {
    TechnicalException exception = new TechnicalException(
        TechnicalErrorMessage.UNEXPECTED_EXCEPTION);
    ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getCode(), Objects.requireNonNull(
        responseEntity.getBody()).getCode());
  }

  @Test
  @DisplayName("Test method handle any HttpRequestMethodNotSupportedException")
  void shouldHandleHttpRequestMethodNotSupportedException() {
    Exception exception = new HttpRequestMethodNotSupportedException("");
    ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(TechnicalErrorMessage.SERVICE_NOT_FOUND.getCode(), Objects.requireNonNull(
        responseEntity.getBody()).getCode());
  }

  @Test
  @DisplayName("Test method handle any exception")
  void shouldHandleAnyException() {
    RuntimeException exception = new RuntimeException();
    ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleExceptions(exception);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(TechnicalErrorMessage.UNEXPECTED_EXCEPTION.getCode(), Objects.requireNonNull(
        responseEntity.getBody()).getCode());
  }

  @Test
  @DisplayName("Test method handle MissingServletRequestParameter exception")
  void shouldHandleMissingServletRequestParameterException() {
    MissingServletRequestParameterException exception = new MissingServletRequestParameterException(
        "productId", "Long");
    ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleMissingServletRequestParameter(
        exception);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(REQUEST_ERROR, Objects.requireNonNull(
        responseEntity.getBody()).getCode());
  }
}