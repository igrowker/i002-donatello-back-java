package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.PromocionDto;
import com.igrowker.donatello.models.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromocionMapper {

    @Mapping(target = "idUsuario", source = "usuarioId")
    Promocion toPromocion(PromocionDto promocionDto);

    @Mapping(target = "usuarioId", source = "idUsuario")
    PromocionDto toPromocionDto(Promocion promocion);

    List<PromocionDto> toPromocionesDtos(List<Promocion> promociones);

    @Mapping(source = "usuarioId", target = "idUsuario")
    void updatePromocion(@MappingTarget Promocion promocion, PromocionDto promocionDto);
}
