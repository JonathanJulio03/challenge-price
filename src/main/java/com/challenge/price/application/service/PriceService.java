package com.challenge.price.application.service;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.application.output.PricePort;
import com.challenge.price.domain.strategy.PriceStrategyFactory;
import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.commons.exception.message.BusinessErrorMessage;
import com.challenge.price.server.models.PriceDto;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUseCase {

  private final PricePort port;

  private final PriceStrategyFactory strategyFactory;

  @Override
  public PriceDto getPrice(LocalDateTime date, Long productId, Long brandId) {
    return Optional.ofNullable(port.getPrices(date, productId, brandId))
        .filter(prices -> !prices.isEmpty())
        .map(strategyFactory::resolve)
        .map(strategy -> strategy.apply(port.getPrices(date, productId, brandId)))
        .orElseThrow(() -> new BusinessException(BusinessErrorMessage.PRICE_APPLY_NOT_FOUND));
  }
}