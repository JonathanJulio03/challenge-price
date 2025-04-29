package com.challenge.price.infrastructure.input.adapter.rest.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.domain.PriceModel;
import com.challenge.price.infrastructure.input.adapter.rest.command.response.PriceDto;
import com.challenge.price.infrastructure.input.adapter.rest.mapper.PriceMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  @InjectMocks
  private PriceController controller;

  @Mock
  private PriceUseCase useCase;

  @Spy
  private PriceMapper mapper = Mappers.getMapper(PriceMapper.class);

  public static final long ID = 1;

  @Test
  @DisplayName("Test method return PriceDto")
  void getPriceApplyReturnsPriceDto() {
    LocalDateTime date = LocalDateTime.now();
    PriceDto priceDto = new PriceDto();
    PriceModel priceModel = PriceModel.builder().build();

    when(useCase.getPrice(date, ID, ID)).thenReturn(priceModel);
    when(mapper.toDto(priceModel)).thenReturn(priceDto);

    ResponseEntity<PriceDto> response = controller.getPriceApply(date, ID, ID);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(priceDto, response.getBody());
    verify(useCase, times(1)).getPrice(date, ID, ID);
  }
}