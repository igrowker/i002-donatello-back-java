package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.services.IProductService;
import com.igrowker.donatello.validators.IProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductValidator productValidator;

    @GetMapping("/inventory")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/inventory")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDto) {
        productValidator.validate(productDto);
        return new ResponseEntity<>(productService.add(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDto) {
        productValidator.validate(productDto);
        return new ResponseEntity<>(productService.update(id, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}
