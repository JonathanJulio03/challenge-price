package com.challenge.price.commons.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage {
  PRICE_APPLY_NOT_FOUND("PAB000001", "Precio a aplicar no encontrado", HttpStatus.NOT_FOUND),
  REQUEST_ERROR("PAB000002", "Request erroneo", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String message;
  final HttpStatus httpStatus;
}
