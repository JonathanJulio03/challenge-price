package com.challenge.price.infrastructure.output.adapter.db.repository;

import com.challenge.price.infrastructure.output.adapter.db.entities.PriceData;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceData, Long> {

  List<PriceData> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandDataId(
      LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId);
}
