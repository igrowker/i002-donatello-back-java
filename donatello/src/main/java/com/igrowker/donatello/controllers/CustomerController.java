package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.services.ICustomerService;
import com.igrowker.donatello.validators.ICustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CustomerDTO customerDto){
        customerValidator.validate(customerDto);
        return new ResponseEntity<>(customerService.add(customerDto), HttpStatus.ACCEPTED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CustomerDTO customerDto){
        customerValidator.validate(customerDto);
        return new ResponseEntity<>(customerService.update(id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
