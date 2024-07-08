package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.CustomerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> getCustomers(HttpHeaders headers);

    CustomerDTO add(HttpHeaders headers, CustomerDTO customerDto);

    CustomerDTO update(HttpHeaders headers, Integer id, CustomerDTO customerDto);

    void delete(HttpHeaders headers,Integer id);
}
