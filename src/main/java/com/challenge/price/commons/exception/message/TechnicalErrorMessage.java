package com.challenge.price.commons.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalErrorMessage {
  SERVICE_NOT_FOUND("PAT000001", "Service not found"),
  UNEXPECTED_EXCEPTION("PAT000002", "Unexpected error"),
  PRICE_APPLY_EXCEPTION("PAT000003", "Error consultando precio a aplicar");


  private final String code;
  private final String message;
}
