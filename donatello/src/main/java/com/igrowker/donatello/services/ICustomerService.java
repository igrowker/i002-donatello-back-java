package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> getCustomers();

    CustomerDTO add(CustomerDTO customerDto);

    CustomerDTO update(Integer id, CustomerDTO customerDto);

    void delete(Integer id);
}
