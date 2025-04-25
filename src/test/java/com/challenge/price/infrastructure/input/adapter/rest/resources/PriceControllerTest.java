package com.challenge.price.infrastructure.input.adapter.rest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.server.models.PriceDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  @InjectMocks
  private PriceController controller;

  @Mock
  private PriceUseCase useCase;

  public static final long ID = 1;

  @Test
  @DisplayName("Test method return PriceDto")
  void getPriceApplyReturnsPriceDto() {
    LocalDateTime date = LocalDateTime.now();
    PriceDto priceDto = new PriceDto();

    when(useCase.getPrice(date, ID, ID)).thenReturn(priceDto);

    ResponseEntity<PriceDto> response = controller.getPriceApply(date, ID, ID);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(priceDto, response.getBody());
    verify(useCase, times(1)).getPrice(date, ID, ID);
  }
}