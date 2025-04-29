package com.challenge.price.infrastructure.input.adapter.rest.mapper;

import com.challenge.price.domain.PriceModel;
import com.challenge.price.infrastructure.input.adapter.rest.command.response.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceMapper {

  PriceDto toDto(PriceModel priceModel);

}
