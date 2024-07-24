package com.igrowker.donatello.controllers;


import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.dtos.ProviderDTO;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.IProviderService;
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
@Tag(name = "Proveedores")
@SecurityRequirement(name = "Bearer Authentication")
public class ProviderController {
    @Autowired
    IProviderService providerService;

    @Operation(summary = "Retorna los proveedores pertenecientes al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna listado de proveedores",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("/providers")
    public ResponseEntity<List<ProviderDTO>> getAll(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(providerService.getAll(headers), HttpStatus.OK);
    }

    @Operation(summary = "Crea un proveedor perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorn proveedor creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping("/providers")
    public ResponseEntity<ProviderDTO> save(@RequestHeader HttpHeaders headers, @RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.save(headers,providerDTO), HttpStatus.OK);
    }

    @Operation(summary = "Permite edicion de un proveedor perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna proveedor editado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Promocion no encontrada por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping("/providers/{id}")
    public ResponseEntity<ProviderDTO> update(@RequestHeader HttpHeaders headers, @PathVariable Long id, @RequestBody ProviderDTO providerDTO){
        return new ResponseEntity<>(providerService.Update(headers,id,providerDTO), HttpStatus.OK);
    }

    @Operation(summary = "Permite eliminar un proveedor perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna proveedor eliminada",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @DeleteMapping("/providers/{id}")
    public ResponseEntity<?> delete(@RequestHeader HttpHeaders headers,@PathVariable Long id){
        providerService.DeleteById(headers,id);
        return ResponseEntity.ok("Successfully deleted!");
    }
}
