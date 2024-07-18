package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    Boolean existsByName(String name);
}
