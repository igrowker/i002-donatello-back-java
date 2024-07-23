package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Menu")
@SecurityRequirement(name = "Bearer Authentication")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Operation(summary = "Crea un menu perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna menu creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping("/menu")
    public ResponseEntity<MenuDto> saveMenu(@RequestHeader HttpHeaders headers, @RequestBody MenuDto menuDto){
        return new ResponseEntity<>(menuService.save(headers,menuDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Retorna los menus pertenecientes al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna listado de menus",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("/menu")
    public ResponseEntity<List<MenuDto>> getMenus(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(menuService.getMenus(headers), HttpStatus.OK);
    }

    @Operation(summary = "Permite edicion de un menu perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna menu editado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuDto> updateMenu(@RequestHeader HttpHeaders headers,@PathVariable Integer id, @RequestBody MenuDto menuDto){
        return new ResponseEntity<>(menuService.update(headers,id, menuDto), HttpStatus.OK);
    }

    @Operation(summary = "Permite eliminar un menu perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna menu eliminado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @DeleteMapping("/menu/{id}")
    public ResponseEntity<?> deleteMenu(@RequestHeader HttpHeaders headers, @PathVariable Integer id){
        return new ResponseEntity<>(menuService.delete(headers, id), HttpStatus.OK);
    }
}
