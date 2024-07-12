package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.dtos.ProviderDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProviderService {
    List<ProviderDTO> getAll(HttpHeaders headers, ProviderDTO providerDTO);

    void save(HttpHeaders headers ,ProviderDTO providerDTO);

    ProviderDTO FindById(Long Id);

    void Update(Long Id, ProviderDTO providerDTO);

    void DeleteById(Long id);
}