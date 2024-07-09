package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Customer;
import com.igrowker.donatello.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.user.id = :idUser")
    List<Customer> findAllByIdCustomUser(Integer idUser);
}
