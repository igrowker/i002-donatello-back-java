package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.PromotionMapper;
import com.igrowker.donatello.models.Promotion;
import com.igrowker.donatello.repositories.PromotionRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.PromotionService;
import com.igrowker.donatello.validators.PromotionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    @Autowired
    private PromotionValidator promotionValidator;

    @Override
    public PromotionDto save(PromotionDto promotionDto) {
        LocalDateTime date = LocalDateTime.now();
        promotionDto.setStartDate(date);
        promotionValidator.validate(promotionDto);
        Promotion promotion = promotionMapper.toPromotion(promotionDto);
        promotion.setCustomUser(userRepository.getReferenceById(promotionDto.getUserId()));

        return promotionMapper.toPromotionDto(promotionRepository.save(promotion));
    }

    @Override
    public List<PromotionDto> getPromotions() {
        return promotionMapper.toPromotionsDto(promotionRepository.findAll());
    }

    @Override
    public PromotionDto update(Integer id, PromotionDto promotionDto) {
        LocalDateTime date = LocalDateTime.now();
        promotionDto.setStartDate(date);
        promotionValidator.validate(promotionDto);
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found!"));
        promotion.setCustomUser(userRepository.getReferenceById(promotionDto.getUserId()));
        promotionMapper.updatePromotion(promotion, promotionDto);
        return promotionMapper.toPromotionDto(promotionRepository.save(promotion));
    }

    @Override
    public PromotionDto delete(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found!"));
        PromotionDto promotionDto = promotionMapper.toPromotionDto(promotion);
        promotionRepository.deleteById(id);
        return promotionDto;
    }
}
