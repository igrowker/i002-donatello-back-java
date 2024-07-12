package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByIdUser(Integer userId);
}
