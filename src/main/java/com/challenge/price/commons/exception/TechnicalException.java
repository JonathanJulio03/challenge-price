package com.challenge.price.commons.exception;

import com.challenge.price.commons.exception.message.TechnicalErrorMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TechnicalException extends RuntimeException {

  final TechnicalErrorMessage technicalErrorMessage;
}
