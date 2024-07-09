package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.dtos.ProviderDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProviderService {
    List<ProviderDTO> getProviders(HttpHeaders headers);

    ProviderDTO add(HttpHeaders headers, ProviderDTO providerDTO);

    ProviderDTO update(HttpHeaders headers,Long providerId, ProviderDTO providerDTO);

    ProviderDTO delete(HttpHeaders headers,Long providerId);

}
