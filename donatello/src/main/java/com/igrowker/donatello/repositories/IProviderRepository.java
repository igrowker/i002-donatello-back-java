package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.ProviderEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProviderRepository extends JpaRepository<ProviderEntity,Long> {
}
