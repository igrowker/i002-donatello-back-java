package com.igrowker.donatello.services.impl;
import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.exceptions.BadRequestException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.IProviderMapper;
import com.igrowker.donatello.mappers.ProductMapper;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.models.ProviderEntity;
import com.igrowker.donatello.repositories.IProviderRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProviderDTO> getAll(HttpHeaders headers) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        return providerMapper.ProviderToProviderDtoList(iProviderRepository.findAllByUserId(userId));
    }
    @Override
    public ProviderDTO save(HttpHeaders headers, ProviderDTO providerDTO) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        providerDTO.setUserId(userId);
        providerDTO.setProductList(new ArrayList<ProductDTO>());
        ProviderEntity provider = providerMapper.ProviderDtoToProvider(providerDTO);
        provider.setUser(iUserRepository.getReferenceById(providerDTO.getUserId()));
        return providerMapper.ProviderToProviderDTO(iProviderRepository.save(provider));
    }

    @Override
    public ProviderDTO Update(HttpHeaders headers , Long id, ProviderDTO providerDTO) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        providerDTO.setId(id); // evita error de usuario null
        ProviderEntity provider = getProviderbyId(id);

        if(provider.getUser().getId().equals(userId)){
            // todo porque setea nuevamente el usuario? provider.setUser(iUserRepository.getReferenceById(userId));
            providerMapper.updateProvider(provider,providerDTO);
            return providerMapper.ProviderToProviderDTO(iProviderRepository.save(provider));
        }
        else throw new BadRequestException("You cant update providers statement than don't belongs to you.");
    }

    @Override
    public void DeleteById(HttpHeaders headers ,Long id) {
        Integer userId = iAuthService.getLoguedUser(headers).getId();
        ProviderEntity provider = getProviderbyId(id);
        if (provider.getUser().getId().equals(userId)){
            ProviderDTO providerDTO = providerMapper.ProviderToProviderDTO(provider);
            iProviderRepository.deleteById(providerDTO.getId());
        }
        else throw new BadRequestException("You cant delete providers statement than don't belongs to you.");
    }

    @Override
    public void addNewProduct(Long id, Product product) {
        ProviderEntity provider = getProviderbyId(id);
        provider.getProductList().add(product);
        iProviderRepository.save(provider);
    }

    @Override
    public boolean existProductInProvider(ProductDTO dto) {
        List<Product> products = getProviderbyId(dto.getProviderId()).getProductList();
        return products.stream().anyMatch(product -> product.getName().equals(dto.getName()));
    }

    @Override
    public void deleteProduct(Long id, Product product) {
        ProviderEntity provider = getProviderbyId(id);
        provider.getProductList().remove(product);
        iProviderRepository.save(provider);
    }

    @Override
    public List<ProductDTO> findAllProductsByIdUser(Integer id) {
        List<ProviderEntity> proveedores = getAllProvidersByIdUser(id);
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (int i = 0; i<proveedores.size(); i++){
            proveedores.get(i).getProductList().forEach(p-> productDTOS.add(productMapper.toProductDto(p)));
        }
        return productDTOS;
    }

    @Override
    public List<ProviderEntity> getAllProvidersByIdUser(Integer id){
        return iProviderRepository.findAllByUserId(id);
    }

    @Override
    public ProviderEntity getProviderbyId(Long id){
        return iProviderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("The provider doesnt exist"));
    }
}