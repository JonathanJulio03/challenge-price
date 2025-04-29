package com.challenge.price.application.service;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.application.output.PricePort;
import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.commons.exception.message.BusinessErrorMessage;
import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUseCase {

  private final PricePort port;

  @Override
  public PriceModel getPrice(LocalDateTime date, Long productId, Long brandId) {
    return Optional.ofNullable(port.getPrices(date, productId, brandId))
        .flatMap(prices -> prices)
        .orElseThrow(() -> new BusinessException(BusinessErrorMessage.PRICE_APPLY_NOT_FOUND));
  }
}