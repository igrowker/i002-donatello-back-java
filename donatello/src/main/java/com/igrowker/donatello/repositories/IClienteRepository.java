package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
