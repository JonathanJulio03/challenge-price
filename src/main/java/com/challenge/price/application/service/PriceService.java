package com.challenge.price.application.service;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.application.output.PricePort;
import com.challenge.price.commons.exception.BusinessException;
import com.challenge.price.commons.exception.message.BusinessErrorMessage;
import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUseCase {

  private final PricePort port;

  @Override
  public PriceModel getPrice(LocalDateTime date, Long productId, Long brandId) {
    List<PriceModel> priceModelApply = port.getPrices(date, productId, brandId);

    if (priceModelApply.isEmpty()) {
      throw new BusinessException(BusinessErrorMessage.PRICE_APPLY_NOT_FOUND);
    }

    return priceModelApply.stream().max(Comparator.comparingInt(PriceModel::getPriority))
        .orElseThrow(() -> new BusinessException(BusinessErrorMessage.PRICE_APPLY_ERROR));
  }
}