package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.dtos.finances.FinanceIncomeDto;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.IFinancesService;
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
@RequestMapping("api/finances/")
@Tag(name = "Finanzas")
@SecurityRequirement(name = "Bearer Authentication")
public class FinancesController {

    @Autowired
    private IFinancesService financesService;


    @Operation(summary = "Retorna las transacciones pertenecientes al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna listado de transacciones",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("transactions/")
    public ResponseEntity<List<FinanceDTO>> getAll(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(financesService.getAll(headers), HttpStatus.OK);
    }

    @Operation(summary = "Crea una nueva transaccion perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna transaccion creada",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping("transactions/")
    public ResponseEntity<FinanceDTO> create(@RequestHeader HttpHeaders headers, @RequestBody FinanceDTO dto) {
        return new ResponseEntity<>(financesService.create(headers, dto), HttpStatus.OK);
    }

    @Operation(summary = "Retorna la transaccion perteneciente al usuario logueado,segun ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna transaccion por ID",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Transaccion no encontrada por ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("transactions/{id}")
    public ResponseEntity<FinanceDTO> getById(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        return new ResponseEntity<>(financesService.getById(headers, id), HttpStatus.OK);
    }

    @Operation(summary = "Permite edicion de una transaccion perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna transaccion editada",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Transaccion no encontrada por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping("transactions/{id}")
    public ResponseEntity<FinanceDTO> edit(@RequestHeader HttpHeaders headers, @PathVariable Integer id, @RequestBody FinanceDTO dto) {
        return new ResponseEntity<>(financesService.edit(headers, id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Permite eliminar una transaccion perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna transaccion eliminada",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Transaccion no encontrado por id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @DeleteMapping("transactions/{id}")
    public ResponseEntity<Void> delete(@RequestHeader HttpHeaders headers, @PathVariable Integer id) {
        financesService.delete(headers, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Operation(summary = "PENDIENTEEEE === >>> Retorna reportes de transacciones perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna reportes de transacciones",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("reports/")
    public ResponseEntity<List<FinanceDTO>> getReports(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(financesService.getReports(headers), HttpStatus.OK);
    }

    @Operation(summary = "PENDIENTEEEE === >>> Retorna reportes de transacciones perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna reportes de transacciones",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("/incomes")
    public ResponseEntity<FinanceIncomeDto> getIncomes(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(financesService.getIncomes(headers), HttpStatus.OK);
    }
}
