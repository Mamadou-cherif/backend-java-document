package com.smshub.org.features.utilisateur.repository;

import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // Corrected method to find Utilisateurs by Structure ID

    @Query("SELECT u FROM Utilisateur u JOIN u.structures s WHERE s.id = :structureId")
    List<Utilisateur> findByStructureId(@Param("structureId") Long structureId);
}
