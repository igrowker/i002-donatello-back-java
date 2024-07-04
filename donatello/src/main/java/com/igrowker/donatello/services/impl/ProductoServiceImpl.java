package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.ProductoDto;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProductoMapper;
import com.igrowker.donatello.models.Producto;
import com.igrowker.donatello.repositories.ProductoRepository;
import com.igrowker.donatello.repositories.UserRepository;
import com.igrowker.donatello.services.ProductoService;
import com.igrowker.donatello.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductValidator productValidator;
    @Override
    public ProductoDto save(ProductoDto productoDto) {
        productValidator.validate(productoDto);
        Boolean existsProducto = productoRepository.existsByNombre(productoDto.getNombre());
        if (!existsProducto){
           Producto producto = productoMapper.toProducto(productoDto);
           producto.setCustomUser(userRepository.getReferenceById(productoDto.getUsuarioId()));
           return productoMapper.toProductoDto(productoRepository.save(producto));
        }else {
            throw new ConflictException("El producto existe!");
        }
    }

    @Override
    public List<ProductoDto> getProductos() {
        return productoMapper.toProductosDtos(productoRepository.findAll());
    }

    @Override
    public ProductoDto update(Integer productoId, ProductoDto productoDto) {
        productValidator.validate(productoDto);
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(()-> new NotFoundException("Producto not found!"));
        producto.setCustomUser(userRepository.getReferenceById(productoDto.getUsuarioId()));
        productoMapper.updateProducto(producto, productoDto);
        return productoMapper.toProductoDto(productoRepository.save(producto));
    }

    @Override
    public ProductoDto delete(Integer productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(()-> new NotFoundException("Producto not found!"));
        ProductoDto productoDto = productoMapper.toProductoDto(producto);
        productoRepository.deleteById(productoId);
        return productoDto;
    }
}
