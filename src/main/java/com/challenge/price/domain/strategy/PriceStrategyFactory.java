package com.challenge.price.domain.strategy;

import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.commons.exception.message.BusinessErrorMessage;
import com.challenge.price.domain.PriceModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceStrategyFactory {

  private final List<PriceStrategy> strategies;

  public PriceStrategy resolve(List<PriceModel> prices) {
    return strategies.stream()
        .filter(strategy -> strategy.isApplicable(prices))
        .findFirst()
        .orElseThrow(() -> new BusinessException(BusinessErrorMessage.PRICE_APPLY_ERROR));
  }
}
