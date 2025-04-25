package com.challenge.price.domain.strategy;

import com.challenge.price.domain.PriceModel;
import com.challenge.price.server.models.PriceDto;
import java.util.List;

public interface PriceStrategy {
    boolean isApplicable(List<PriceModel> prices);
    PriceDto apply(List<PriceModel> prices);
}
