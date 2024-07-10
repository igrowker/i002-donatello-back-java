package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.models.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mapping(target = "idUser", source = "userId")
    @Mapping(target = "id", source = "id")
    Promotion toPromotion(PromotionDto promotionDto);

    @Mapping(target = "userId", source = "idUser")
    @Mapping(target = "id", source = "id")
    PromotionDto toPromotionDto(Promotion promotion);

    List<PromotionDto> toPromotionsDto(List<Promotion> promotions);

    @Mapping(source = "userId", target = "idUser")
    @Mapping(target = "id", source = "id")
    void updatePromotion(@MappingTarget Promotion promotion, PromotionDto promotionDto);
}
