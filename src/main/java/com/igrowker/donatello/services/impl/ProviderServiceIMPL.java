package com.igrowker.donatello.services.impl;
import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.exceptions.BadRequestException;
import com.igrowker.donatello.mappers.IProviderMapper;
import com.igrowker.donatello.models.ProviderEntity;
import com.igrowker.donatello.repositories.IProviderRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProviderServiceIMPL implements IProviderService {
    @Autowired
    private IProviderRepository iProviderRepository;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    IAuthService iAuthService;
    @Autowired
    IProviderMapper providerMapper;

    @Override
    public List<ProviderDTO> getAll(HttpHeaders headers) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        return providerMapper.ProviderToProviderDtoList(iProviderRepository.findAllByUserId(userId));
    }

    @Override
    public ProviderDTO save(HttpHeaders headers, ProviderDTO providerDTO) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        providerDTO.setUserId(userId);
        ProviderEntity provider = providerMapper.ProviderDtoToProvider(providerDTO);
        provider.setUser(iUserRepository.getReferenceById(providerDTO.getUserId()));
        return providerMapper.ProviderToProviderDTO(iProviderRepository.save(provider));
    }

    @Override
    public ProviderDTO Update(HttpHeaders headers , Long Id, ProviderDTO providerDTO) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        ProviderEntity provider = iProviderRepository.findById(Id)
                .orElseThrow(()-> new BadRequestException("The provider doesnt exist"));
        if(provider.getUser().getId().equals(userId)){
            provider.setUser(iUserRepository.getReferenceById(userId));
            providerMapper.updateProvider(provider,providerDTO);
            return providerMapper.ProviderToProviderDTO(iProviderRepository.save(provider));
        }
        else throw new BadRequestException("You cant update providers statement than don't belongs to you.");
    }

    @Override
    public void DeleteById(HttpHeaders headers ,Long id) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        ProviderEntity provider = iProviderRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("The provider doesnt exist"));
        if (provider.getUser().getId().equals(userId)){
            ProviderDTO providerDTO = providerMapper.ProviderToProviderDTO(provider);
            iProviderRepository.deleteById(providerDTO.getId());
        }
        else throw new BadRequestException("You cant delete providers statement than don't belongs to you.");
    }
}