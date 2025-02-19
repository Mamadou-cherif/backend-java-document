package com.smshub.org.features.structure.repository;


import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Integer> {
    List<Utilisateur> findByStructures_Id(Integer structureId);

}
