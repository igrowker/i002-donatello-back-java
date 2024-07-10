package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.models.Product;
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
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.idUser( productDto.getUserID() );
        product.id( productDto.getId() );
        product.name( productDto.getName() );
        product.description( productDto.getDescription() );
        product.stock( productDto.getStock() );

        return product.build();
    }

    @Override
    public ProductDTO toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.userID( product.getIdUser() );
        productDTO.id( product.getId() );
        productDTO.name( product.getName() );
        productDTO.description( product.getDescription() );
        productDTO.stock( product.getStock() );

        return productDTO.build();
    }

    @Override
    public List<ProductDTO> toProductsDto(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }

    @Override
    public void updateProduct(Product product, ProductDTO productDto) {
        if ( productDto == null ) {
            return;
        }

        product.setIdUser( productDto.getUserID() );
        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setDescription( productDto.getDescription() );
        product.setStock( productDto.getStock() );
    }
}
