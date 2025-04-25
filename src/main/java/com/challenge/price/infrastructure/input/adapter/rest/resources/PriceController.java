package com.challenge.price.infrastructure.input.adapter.rest.resources;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.commons.helper.Constants;
import com.challenge.price.server.PriceApi;
import com.challenge.price.server.models.PriceDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = Constants.APP_SAFE, methods = RequestMethod.GET)
@RequiredArgsConstructor
public class PriceController implements PriceApi {

  private final PriceUseCase useCase;

  @Override
  public ResponseEntity<PriceDto> getPriceApply(LocalDateTime date, Long productId, Long brandId) {
    log.info("Consulta de precio para el producto {} y la marca {}", productId, brandId);
    return ResponseEntity.ok(useCase.getPrice(date, productId, brandId));
  }
}
