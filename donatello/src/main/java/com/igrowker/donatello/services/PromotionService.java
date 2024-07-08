package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.PromotionDto;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface PromotionService {

    PromotionDto save(HttpHeaders headers,PromotionDto promotionDto);

    List<PromotionDto> getPromotions();

    PromotionDto update(HttpHeaders headers,Integer id, PromotionDto promotionDto);

    PromotionDto delete(HttpHeaders headers,Integer id);
}
