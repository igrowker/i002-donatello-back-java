package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.ClienteDTO;
import com.igrowker.donatello.services.IClienteService;
import com.igrowker.donatello.validators.IClienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class ClienteController {

    @Autowired
    IClienteService serviceCliente;
    @Autowired
    IClienteValidator clienteValidator;

    @GetMapping
    public ResponseEntity<?> listarMenus(){
        return new ResponseEntity<>(serviceCliente.listarClientes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody ClienteDTO clienteDto){
        clienteValidator.validar(clienteDto);
        return new ResponseEntity<>(serviceCliente.registrar(clienteDto), HttpStatus.ACCEPTED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> modificar(@PathVariable("id") Integer id, @RequestBody ClienteDTO clienteDto){
        clienteValidator.validar(clienteDto);
        return new ResponseEntity<>(serviceCliente.modificar(id, clienteDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id){
        serviceCliente.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
