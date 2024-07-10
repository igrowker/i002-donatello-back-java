package com.igrowker.donatello.mappers;

import com.igrowker.donatello.auth.entities.CustomUser;
import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.models.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-10T16:57:09-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        if ( customerDto.getId() != null ) {
            customer.setId( customerDto.getId() );
        }
        customer.setName( customerDto.getName() );
        customer.setPhone( customerDto.getPhone() );
        customer.setAddress( customerDto.getAddress() );

        return customer;
    }

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setUserID( customerUserId( customer ) );
        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setPhone( customer.getPhone() );
        customerDTO.setAddress( customer.getAddress() );

        return customerDTO;
    }

    private Integer customerUserId(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        CustomUser user = customer.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
