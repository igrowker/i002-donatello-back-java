package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.MenuProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuProductRepository extends JpaRepository<MenuProduct, Integer> {
}
