package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.IPromotionService;
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
@Tag(name = "Promociones")
@SecurityRequirement(name = "Bearer Authentication")
public class PromotionController {

    @Autowired
    private IPromotionService promotionService;

    @Operation(summary = "Crea una promocion perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna promocion creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PromotionDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping("/promotions")
    public ResponseEntity<PromotionDto> savePromotion(@RequestHeader HttpHeaders headers, @RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.save(headers,promotionDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna los promociones pertenecientes al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna listado de promociones",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PromotionDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("/promotions")
    public ResponseEntity<List<PromotionDto>> getPromotions(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(promotionService.getPromotions(headers), HttpStatus.OK);
    }

    @Operation(summary = "Permite edicion de una promocion perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna promocion editado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PromotionDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Promocion no encontrada por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping("/promotions/{id}")
    public ResponseEntity<PromotionDto> updatePromotion(@RequestHeader HttpHeaders headers, @PathVariable Integer id, @RequestBody PromotionDto promotionDto){
        return new ResponseEntity<>(promotionService.update(headers, id, promotionDto), HttpStatus.OK);
    }

    @Operation(summary = "Permite eliminar una promocion perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna promocion eliminada",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PromotionDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<?> deletePromotion(@RequestHeader HttpHeaders headers, @PathVariable Integer id){
        return new ResponseEntity<>(promotionService.delete(headers, id), HttpStatus.OK);
    }
}
