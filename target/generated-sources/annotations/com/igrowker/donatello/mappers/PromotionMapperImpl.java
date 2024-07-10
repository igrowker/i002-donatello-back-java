package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.models.Promotion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-10T16:57:10-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PromotionMapperImpl implements PromotionMapper {

    @Override
    public Promotion toPromotion(PromotionDto promotionDto) {
        if ( promotionDto == null ) {
            return null;
        }

        Promotion.PromotionBuilder promotion = Promotion.builder();

        promotion.idUser( promotionDto.getUserId() );
        promotion.id( promotionDto.getId() );
        promotion.description( promotionDto.getDescription() );
        promotion.startDate( promotionDto.getStartDate() );
        promotion.endDate( promotionDto.getEndDate() );

        return promotion.build();
    }

    @Override
    public PromotionDto toPromotionDto(Promotion promotion) {
        if ( promotion == null ) {
            return null;
        }

        PromotionDto.PromotionDtoBuilder promotionDto = PromotionDto.builder();

        promotionDto.userId( promotion.getIdUser() );
        promotionDto.id( promotion.getId() );
        promotionDto.description( promotion.getDescription() );
        promotionDto.startDate( promotion.getStartDate() );
        promotionDto.endDate( promotion.getEndDate() );

        return promotionDto.build();
    }

    @Override
    public List<PromotionDto> toPromotionsDto(List<Promotion> promotions) {
        if ( promotions == null ) {
            return null;
        }

        List<PromotionDto> list = new ArrayList<PromotionDto>( promotions.size() );
        for ( Promotion promotion : promotions ) {
            list.add( toPromotionDto( promotion ) );
        }

        return list;
    }

    @Override
    public void updatePromotion(Promotion promotion, PromotionDto promotionDto) {
        if ( promotionDto == null ) {
            return;
        }

        promotion.setIdUser( promotionDto.getUserId() );
        promotion.setId( promotionDto.getId() );
        promotion.setDescription( promotionDto.getDescription() );
        promotion.setStartDate( promotionDto.getStartDate() );
        promotion.setEndDate( promotionDto.getEndDate() );
    }
}
