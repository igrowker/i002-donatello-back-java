package com.igrowker.donatello.repositories;

import com.igrowker.donatello.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    @Query("SELECT p FROM perfiles p JOIN p.usuario u WHERE u.id = :idUsuario")
    Optional<Profile> findByIdUsuario(Integer idUsuario);
}
