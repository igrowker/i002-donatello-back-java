package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Integer> {
    Optional<CustomUser> findByMail(String mail);
    Boolean existsByMail(String mail);
}
