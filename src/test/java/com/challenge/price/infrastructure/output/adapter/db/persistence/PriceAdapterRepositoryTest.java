package com.challenge.price.infrastructure.output.adapter.db.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.price.commons.exception.TechnicalException;
import com.challenge.price.domain.PriceModel;
import com.challenge.price.infrastructure.output.adapter.db.entities.PriceData;
import com.challenge.price.infrastructure.output.adapter.db.mapper.PriceDataMapper;
import com.challenge.price.infrastructure.output.adapter.db.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceAdapterRepositoryTest {

  @Mock
  private PriceRepository repository;

  @Spy
  private PriceDataMapper mapper = Mappers.getMapper(PriceDataMapper.class);

  @InjectMocks
  private PriceAdapterRepository priceAdapterRepository;

  public static final long ID = 1;

  private LocalDateTime currentDate;

  @BeforeEach
  void setUp() {
    currentDate = LocalDateTime.now();
  }

  @Test
  @DisplayName("Test method return Price Models When Repository Returns Data")
  void getPricesReturnsListOfPriceModelsWhenRepositoryReturnsData() {
    when(
        repository.findTopByDateAndProductIdAndBrandId(
            currentDate, ID, ID)).thenReturn(Optional.of(new PriceData()));

    Optional<PriceModel> prices = priceAdapterRepository.getPrices(currentDate, ID, ID);

    assertNotNull(prices);
    verify(repository,
        times(1)).findTopByDateAndProductIdAndBrandId(
        currentDate, ID, ID);
  }

  @Test
  @DisplayName("Test method return Throws TechnicalException When Repository ThrowsException")
  void getPricesThrowsTechnicalExceptionWhenRepositoryThrowsException() {
    when(
        repository.findTopByDateAndProductIdAndBrandId(
            currentDate, ID, ID)).thenThrow(new RuntimeException());

    assertThrows(TechnicalException.class, () ->
        priceAdapterRepository.getPrices(currentDate, ID, ID)
    );
    verify(repository,
        times(1)).findTopByDateAndProductIdAndBrandId(
        currentDate, ID, ID);
    verify(mapper, times(0)).toDomain(any());
  }
}