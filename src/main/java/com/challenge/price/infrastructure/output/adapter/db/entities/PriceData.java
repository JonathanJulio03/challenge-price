package com.challenge.price.infrastructure.output.adapter.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "Prices")
@Table(name = "prices")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  BrandData brandData;

  @Column(name = "product_id")
  Long productId;

  @Column(name = "start_date")
  LocalDateTime startDate;

  @Column(name = "end_date")
  LocalDateTime endDate;

  Integer priority;

  @Column(name = "price_list")
  Integer priceList;

  BigDecimal price;
  String curr;
}
