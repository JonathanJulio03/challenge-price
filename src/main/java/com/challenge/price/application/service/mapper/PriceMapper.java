package com.challenge.price.application.service.mapper;

import com.challenge.price.domain.PriceModel;
import com.challenge.price.server.models.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceMapper {

  PriceDto toDto(PriceModel priceModel);

}
