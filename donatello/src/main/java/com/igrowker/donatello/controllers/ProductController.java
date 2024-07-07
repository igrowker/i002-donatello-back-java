package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.services.IProductService;
import com.igrowker.donatello.validators.IProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<ProductDTO> addProduct(@RequestHeader HttpHeaders headers, @RequestBody ProductDTO productDto) {
        productValidator.validate(productDto);
        return new ResponseEntity<>(productService.add(headers, productDto), HttpStatus.CREATED);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestHeader HttpHeaders headers, @PathVariable Integer id, @RequestBody ProductDTO productDto) {
        productValidator.validate(productDto);
        return new ResponseEntity<>(productService.update(headers,id, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteProduct(@RequestHeader HttpHeaders headers,@PathVariable Integer id) {
        return new ResponseEntity<>(productService.delete(headers,id), HttpStatus.OK);
    }
}
