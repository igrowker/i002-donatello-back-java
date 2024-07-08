package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findAllByIdUser(Integer idUser);
}
