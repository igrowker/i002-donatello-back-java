package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.PromotionDto;

import java.util.List;

public interface PromotionService {

    PromotionDto save(PromotionDto promotionDto);

    List<PromotionDto> getPromotions();

    PromotionDto update(Integer id, PromotionDto promotionDto);

    PromotionDto delete(Integer id);
}
