package com.challenge.price.infrastructure.output.adapter.db.repository;

import com.challenge.price.infrastructure.output.adapter.db.entities.PriceData;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceData, Long> {

  @Query(value = """
          SELECT * FROM prices p
          WHERE :date BETWEEN p.start_date AND p.end_date
            AND p.product_id = :productId
            AND p.brand_id = :brandId
          ORDER BY p.priority DESC
          LIMIT 1
      """, nativeQuery = true)
  Optional<PriceData> findTopByDateAndProductIdAndBrandId(
      @Param("date") LocalDateTime date,
      @Param("productId") Long productId,
      @Param("brandId") Long brandId
  );
}
