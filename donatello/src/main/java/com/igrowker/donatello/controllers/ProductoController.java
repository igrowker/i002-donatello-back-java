package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.ProductoDto;
import com.igrowker.donatello.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/inventory")
    public ResponseEntity<ProductoDto> saveProducto(@RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(productoService.save(productoDto), HttpStatus.CREATED);
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<ProductoDto>> getProductos(){
        return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable Integer id, @RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(productoService.update(id, productoDto), HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id){
        return new ResponseEntity<>(productoService.delete(id), HttpStatus.OK);
    }
}
