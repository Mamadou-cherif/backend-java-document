package com.smshub.org.features.utilisateur.repository;

import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
