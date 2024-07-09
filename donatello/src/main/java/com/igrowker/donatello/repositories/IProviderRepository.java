package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.ProviderEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProviderRepository extends JpaRepository<ProviderEntity,Long> {
}
