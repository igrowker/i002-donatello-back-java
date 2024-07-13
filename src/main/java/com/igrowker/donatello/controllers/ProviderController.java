package com.igrowker.donatello.controllers;


import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProviderController {
    @Autowired
    IProviderService providerService;

    @GetMapping("/providers")
    public ResponseEntity<List<ProviderDTO>> getAll(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(providerService.getAll(headers), HttpStatus.OK);
    }
    @PostMapping("/providers")
    public ResponseEntity<ProviderDTO> save(@RequestHeader HttpHeaders headers, @RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.save(headers,providerDTO), HttpStatus.OK);
    }
    @PutMapping("/providers/{id}")
    public ResponseEntity<ProviderDTO> update(@RequestHeader HttpHeaders headers, @PathVariable Long id, @RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.Update(headers,id,providerDTO), HttpStatus.OK);
    }
    @DeleteMapping("/providers/{id}")
    public ResponseEntity<?> delete(@RequestHeader HttpHeaders headers,@PathVariable Long id){
        providerService.DeleteById(headers,id);
        return ResponseEntity.ok("Successfully deleted!");
    }
}
