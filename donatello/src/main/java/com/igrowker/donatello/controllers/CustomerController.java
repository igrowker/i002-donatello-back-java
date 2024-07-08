package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.services.ICustomerService;
import com.igrowker.donatello.validators.ICustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICustomerValidator customerValidator;

    @GetMapping
    public ResponseEntity<?> getCustomers(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<>(customerService.getCustomers(headers), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestHeader HttpHeaders headers, @RequestBody CustomerDTO customerDto) {
        customerValidator.validate(customerDto);
        return new ResponseEntity<>(customerService.add(headers, customerDto), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestHeader HttpHeaders headers, @PathVariable("id") Integer id, @RequestBody CustomerDTO customerDto) {
        customerValidator.validate(customerDto);
        return new ResponseEntity<>(customerService.update(headers,id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@RequestHeader HttpHeaders headers,@PathVariable("id") Integer id) {
        customerService.delete(headers, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
