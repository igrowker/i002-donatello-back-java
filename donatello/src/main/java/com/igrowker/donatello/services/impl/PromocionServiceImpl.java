package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.PromocionDto;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.PromocionMapper;
import com.igrowker.donatello.models.Promocion;
import com.igrowker.donatello.repositories.PromocionRepository;
import com.igrowker.donatello.repositories.UserRepository;
import com.igrowker.donatello.services.PromocionService;
import com.igrowker.donatello.validators.PromocionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromocionServiceImpl implements PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired
    private PromocionMapper promocionMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromocionValidator promocionValidator;

    @Override
    public PromocionDto save(PromocionDto promocionDto) {
        LocalDateTime fecha = LocalDateTime.now();
        promocionDto.setFechaInicio(fecha);
        promocionValidator.validar(promocionDto);
        Promocion promocion = promocionMapper.toPromocion(promocionDto);
        promocion.setCustomUser(userRepository.getReferenceById(promocionDto.getUsuarioId()));

        return promocionMapper.toPromocionDto(promocionRepository.save(promocion));
    }

    @Override
    public List<PromocionDto> getPromociones() {
        return promocionMapper.toPromocionesDtos(promocionRepository.findAll());
    }

    @Override
    public PromocionDto update(Integer id, PromocionDto promocionDto) {
        LocalDateTime fecha = LocalDateTime.now();
        promocionDto.setFechaInicio(fecha);
        promocionValidator.validar(promocionDto);
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Promocion not found!"));
        promocion.setCustomUser(userRepository.getReferenceById(promocionDto.getUsuarioId()));
        promocionMapper.updatePromocion(promocion, promocionDto);
        return promocionMapper.toPromocionDto(promocionRepository.save(promocion));
    }

    @Override
    public PromocionDto delete(Integer id) {
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Promocion not found!"));
        PromocionDto promocionDto = promocionMapper.toPromocionDto(promocion);
        promocionRepository.deleteById(id);
        return promocionDto;
    }
}
