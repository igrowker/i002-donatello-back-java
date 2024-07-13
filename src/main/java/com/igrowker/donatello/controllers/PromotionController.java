package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.services.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    @PostMapping("/promotions")
    public ResponseEntity<PromotionDto> savePromotion(@RequestHeader HttpHeaders headers, @RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.save(headers,promotionDto), HttpStatus.CREATED);
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<PromotionDto>> getPromotions(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(promotionService.getPromotions(headers), HttpStatus.OK);
    }

    @PutMapping("/promotions/{id}")
    public ResponseEntity<PromotionDto> updatePromotion(@RequestHeader HttpHeaders headers, @PathVariable Integer id, @RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.update(headers, id, promotionDto), HttpStatus.OK);
    }

    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<?> deletePromotion(@RequestHeader HttpHeaders headers, @PathVariable Integer id){
        return new ResponseEntity<>(promotionService.delete(headers, id), HttpStatus.OK);
    }
}
