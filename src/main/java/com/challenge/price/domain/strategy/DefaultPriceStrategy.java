package com.challenge.price.domain.strategy;

import com.challenge.price.application.service.mapper.PriceMapper;
import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.commons.exception.message.BusinessErrorMessage;
import com.challenge.price.domain.PriceModel;
import com.challenge.price.server.models.PriceDto;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultPriceStrategy implements PriceStrategy {

  private final PriceMapper mapper;

  @Override
  public boolean isApplicable(List<PriceModel> prices) {
    return true;
  }

  @Override
  public PriceDto apply(List<PriceModel> prices) {
    return mapper.toDto(
        prices.parallelStream()
            .max(Comparator.comparingInt(PriceModel::getPriority))
            .orElseThrow(() -> new BusinessException(BusinessErrorMessage.PRICE_APPLY_ERROR))
    );
  }
}
