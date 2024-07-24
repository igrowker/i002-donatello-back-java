package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.IProductService;
import com.igrowker.donatello.validators.IProductValidator;
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
@Tag(name = "Productos")
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductValidator productValidator;

    @Operation(summary = "Retorna los productos pertenecientes al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna listado de productos",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("/inventory")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(productService.getProducts(headers), HttpStatus.OK);
    }
    @Operation(summary = "Crea un producto perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna producto creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping("/inventory")
    public ResponseEntity<ProductDTO> addProduct(@RequestHeader HttpHeaders headers, @RequestBody ProductDTO productDto) {
        productValidator.validate(productDto);
        return new ResponseEntity<>(productService.add(headers, productDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Permite edicion de un producto perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna producto editado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping("/inventory/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestHeader HttpHeaders headers, @PathVariable Integer id, @RequestBody ProductDTO productDto) {
        productValidator.validate(productDto);
        return new ResponseEntity<>(productService.update(headers,id, productDto), HttpStatus.OK);
    }

    @Operation(summary = "Permite eliminar un producto perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna producto eliminado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Menu no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<?> deleteProduct(@RequestHeader HttpHeaders headers,@PathVariable Integer id) {
        return new ResponseEntity<>(productService.delete(headers,id), HttpStatus.OK);
    }
}
