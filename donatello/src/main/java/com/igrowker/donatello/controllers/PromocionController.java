package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.PromocionDto;
import com.igrowker.donatello.services.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @PostMapping("/promotions")
    public ResponseEntity<PromocionDto> savePromocion(@RequestBody PromocionDto promocionDto){
        return new ResponseEntity<>(promocionService.save(promocionDto), HttpStatus.CREATED);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<PromocionDto>> getPromociones(){
        return new ResponseEntity<>(promocionService.getPromociones(), HttpStatus.OK);
    }

    @PutMapping("/promotions/{id}")
    public ResponseEntity<PromocionDto> updatePromocion(@PathVariable Integer id, @RequestBody PromocionDto promocionDto){
        return new ResponseEntity<>(promocionService.update(id, promocionDto), HttpStatus.OK);
    }

    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<?> deletePromocion(@PathVariable Integer id){
        return new ResponseEntity<>(promocionService.delete(id), HttpStatus.OK);
    }
}
