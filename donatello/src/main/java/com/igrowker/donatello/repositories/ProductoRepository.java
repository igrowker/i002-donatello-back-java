package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Boolean existsByNombre(String nombre);
}
