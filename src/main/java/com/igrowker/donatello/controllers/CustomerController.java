package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.AuthDTO;
import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.ICustomerService;
import com.igrowker.donatello.validators.ICustomerValidator;
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
@RequestMapping("api/customers")
@Tag(name = "Clientes")
@SecurityRequirement(name = "Bearer Authentication")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICustomerValidator customerValidator;

    @Operation(summary = "Retorna los clientes pertenecientes al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna listado de clientes",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(customerService.getCustomers(headers), HttpStatus.OK);
    }
    @Operation(summary = "Crea un cliente perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Retorna cliente creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping
    public ResponseEntity<CustomerDTO> add(@RequestHeader HttpHeaders headers, @RequestBody CustomerDTO customerDto) {
        customerValidator.validate(customerDto);
        return new ResponseEntity<>(customerService.add(headers, customerDto), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Permite actualizar un cliente perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente actualizado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> update(@RequestHeader HttpHeaders headers, @PathVariable("id") Integer id, @RequestBody CustomerDTO customerDto) {
        customerValidator.validate(customerDto);
        return new ResponseEntity<>(customerService.update(headers,id, customerDto), HttpStatus.OK);
    }

    @Operation(summary = "Permite eliminar un cliente perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente eliminado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> delete(@RequestHeader HttpHeaders headers,@PathVariable("id") Integer id) {
        customerService.delete(headers, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
