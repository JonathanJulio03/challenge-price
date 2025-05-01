package com.challenge.price.application.service;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.application.output.PricePort;
import com.challenge.price.domain.PriceModel;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUseCase {

  private final PricePort port;

  @Override
  public PriceModel getPrice(LocalDateTime date, Long productId, Long brandId) {
    return port.getPrice(date, productId, brandId);
  }
}