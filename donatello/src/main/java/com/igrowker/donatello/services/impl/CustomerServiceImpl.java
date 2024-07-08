package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.exceptions.ForbiddenException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.CustomerMapper;
import com.igrowker.donatello.models.CustomUser;
import com.igrowker.donatello.models.Customer;
import com.igrowker.donatello.repositories.ICustomerRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    IAuthService authService;

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map((customer) -> customerMapper.customerToCustomerDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO add(HttpHeaders headers, CustomerDTO customerDto) {
        Integer userId = authService.getLoguedUser(headers).getId();
        customerDto.setUserID(userId);
        Customer customer = customerMapper.customerDTOToCustomer(customerDto);
        customer.setUser(userRepository.getReferenceById(customerDto.getUserID()));
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO update(@RequestHeader HttpHeaders headers, Integer id, CustomerDTO customerDto) {
        // todo verificar como hacer mas eficiente este metodo.. no tiene sentido hacerlo asi, o no es lo ideal almenos
        // todo verificar como hacer mas eficiente este metodo.. no tiene sentido hacerlo asi, o no es lo ideal almenos
        Integer userId = authService.getLoguedUser(headers).getId();
        Optional<Customer> cust = customerRepository.findById(id);
        if(! cust.get().getUser().getId().equals(userId)){
            throw new ForbiddenException("The user cannot update someone else's costumer.");
        }
        customerDto.setUserID(userId);
        Customer customer = customerMapper.customerDTOToCustomer(customerDto);
        customer.setUser(userRepository.getReferenceById(customerDto.getUserID()));
        customer.setId(id);
        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public void delete(@RequestHeader HttpHeaders headers,Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        Integer userId = authService.getLoguedUser(headers).getId();
        if(!customer.get().getUser().getId().equals(userId)){
            throw new ForbiddenException("The user cannot delete someone else's costumer.");
        }
        if (customer.isPresent()) customerRepository.deleteById(id);
        else throw new NotFoundException("ID: " + id);
    }
}
