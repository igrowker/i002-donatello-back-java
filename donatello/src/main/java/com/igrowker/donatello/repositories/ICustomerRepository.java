package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}
