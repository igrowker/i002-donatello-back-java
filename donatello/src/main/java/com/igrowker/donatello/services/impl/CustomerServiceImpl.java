package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.CustomerMapper;
import com.igrowker.donatello.models.Customer;
import com.igrowker.donatello.repositories.ICustomerRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map((customer) -> customerMapper.customerToCustomerDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO add(CustomerDTO customerDto) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDto);
        customer.setUser(userRepository.getReferenceById(customerDto.getUserID()));
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO update(Integer id, CustomerDTO customerDto) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDto);
        customer.setUser(userRepository.getReferenceById(customerDto.getUserID()));
        customer.setId(id);
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public void delete(Integer id) {
        if (customerRepository.existsById(id)) customerRepository.deleteById(id);
        else throw new NotFoundException("ID: " + id);
    }
}
