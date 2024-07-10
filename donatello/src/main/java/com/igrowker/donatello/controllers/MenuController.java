package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.services.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @PostMapping("/menu")
    public ResponseEntity<MenuDto> saveMenu(@RequestHeader HttpHeaders headers, @RequestBody MenuDto menuDto){
        return new ResponseEntity<>(menuService.save(headers,menuDto), HttpStatus.CREATED);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDto>> getMenus(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(menuService.getMenus(headers), HttpStatus.OK);
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuDto> updateMenu(@RequestHeader HttpHeaders headers,@PathVariable Integer id, @RequestBody MenuDto menuDto){
        return new ResponseEntity<>(menuService.update(headers,id, menuDto), HttpStatus.OK);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> deleteMenu(@RequestHeader HttpHeaders headers, @PathVariable Integer id){
        return new ResponseEntity<>(menuService.delete(headers, id), HttpStatus.OK);
    }
}
