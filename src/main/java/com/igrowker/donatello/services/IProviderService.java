package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.models.ProviderEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface IProviderService {
    List<ProviderDTO> getAll(HttpHeaders headers);

    ProviderDTO save(HttpHeaders headers ,ProviderDTO providerDTO);

    ProviderDTO Update(HttpHeaders headers ,Long id, ProviderDTO providerDTO);

    void DeleteById( HttpHeaders headers,Long id);
    void addNewProduct(Long id, Product product);
    boolean existProductInProvider (ProductDTO productDto);
    void deleteProduct(Long id, Product product);
    List<ProductDTO> findAllProductsByIdUser(Integer id);
    ProviderEntity getProviderbyId(Long id);
    List<ProviderEntity> getAllProvidersByIdUser(Integer id);

}
