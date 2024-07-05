package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/promotions")
    public ResponseEntity<PromotionDto> savePromotion(@RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.save(promotionDto), HttpStatus.CREATED);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<PromotionDto>> getPromotions(){
        return new ResponseEntity<>(promotionService.getPromotions(), HttpStatus.OK);
    }

    @PutMapping("/promotions/{id}")
    public ResponseEntity<PromotionDto> updatePromotion(@PathVariable Integer id, @RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.update(id, promotionDto), HttpStatus.OK);
    }

    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable Integer id){
        return new ResponseEntity<>(promotionService.delete(id), HttpStatus.OK);
    }
}
