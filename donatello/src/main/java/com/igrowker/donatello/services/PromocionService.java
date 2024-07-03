package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.PromocionDto;

import java.util.List;

public interface PromocionService {

    PromocionDto save(PromocionDto promocionDto);

    List<PromocionDto> getPromociones();

    PromocionDto update(Integer id, PromocionDto promocionDto);

    PromocionDto delete(Integer id);
}
