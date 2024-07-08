package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", source = "id")
    Customer customerDTOToCustomer(CustomerDTO customerDto);

    @Mapping(target = "userID", source = "customer.user.id")
    @Mapping(target = "id", source = "id")
    CustomerDTO customerToCustomerDTO(Customer customer);
}
