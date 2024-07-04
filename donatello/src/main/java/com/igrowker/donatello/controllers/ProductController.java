package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.ProductDto;
import com.igrowker.donatello.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/inventory")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.update(id, productDto), HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}
