package com.challenge.price.infrastructure.output.adapter.db.mapper;

import com.challenge.price.domain.PriceModel;
import com.challenge.price.infrastructure.output.adapter.db.entities.PriceData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceDataMapper {

  @Mapping(source = "brandData.id", target = "brandId")
  PriceModel toDomain(PriceData priceData);

}
