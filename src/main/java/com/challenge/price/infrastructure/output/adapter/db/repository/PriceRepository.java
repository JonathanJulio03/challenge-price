package com.challenge.price.infrastructure.output.adapter.db.repository;

import com.challenge.price.infrastructure.output.adapter.db.entities.PriceData;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceData, Long> {

  @Query("""
          SELECT p FROM Prices p
          WHERE :date BETWEEN p.startDate AND p.endDate
            AND p.productId = :productId
            AND p.brandData.id = :brandId
          ORDER BY p.priority DESC
      """)
  List<PriceData> findTopByDateAndProductIdAndBrandId(
      @Param("date") LocalDateTime date,
      @Param("productId") Long productId,
      @Param("brandId") Long brandId
  );
}
