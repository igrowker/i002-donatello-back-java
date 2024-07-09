package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.exceptions.ForbiddenException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.PromotionMapper;
import com.igrowker.donatello.models.Promotion;
import com.igrowker.donatello.repositories.PromotionRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.PromotionService;
import com.igrowker.donatello.validators.PromotionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    IAuthService authService;

    @Override
    public PromotionDto save(HttpHeaders headers,PromotionDto promotionDto) {
        LocalDateTime date = LocalDateTime.now();
        promotionDto.setStartDate(date);
        Integer userId = authService.getLoguedUser(headers).getId();
        promotionDto.setUserId(userId);
        promotionValidator.validate(promotionDto);
        Promotion promotion = promotionMapper.toPromotion(promotionDto);
        promotion.setCustomUser(userRepository.getReferenceById(promotionDto.getUserId()));

        return promotionMapper.toPromotionDto(promotionRepository.save(promotion));
    }

    @Override
    public List<PromotionDto> getPromotions(HttpHeaders headers) {
        Integer userId = authService.getLoguedUser(headers).getId();
        return promotionMapper.toPromotionsDto(promotionRepository.findAllByIdUser(userId));
    }

    @Override
    public PromotionDto update(HttpHeaders headers,Integer id, PromotionDto promotionDto) {
        LocalDateTime date = LocalDateTime.now();
        promotionDto.setStartDate(date);
        promotionValidator.validate(promotionDto);
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found!"));

        Integer userId = authService.getLoguedUser(headers).getId();
        if(! promotion.getIdUser().equals(userId)) throw new ForbiddenException("The user cannot update someone else's promotion!");
        promotionDto.setUserId(userId);

        promotion.setCustomUser(userRepository.getReferenceById(promotionDto.getUserId()));
        promotionMapper.updatePromotion(promotion, promotionDto);
        return promotionMapper.toPromotionDto(promotionRepository.save(promotion));
    }

    @Override
    public PromotionDto delete(HttpHeaders headers,Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found!"));

        Integer userId = authService.getLoguedUser(headers).getId();
        if(! promotion.getCustomUser().getId().equals(userId)){
            throw new ForbiddenException("The user cannot delete someone else's promotion!");
        }
        PromotionDto promotionDto = promotionMapper.toPromotionDto(promotion);
        promotionRepository.deleteById(id);
        return promotionDto;
    }
}
