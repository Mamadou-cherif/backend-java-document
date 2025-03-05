package com.smshub.org.features.structure.repository;


import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Integer> {
  //  List<Utilisateur> findByStructures_Id(Integer structureId);
    // Trouver les personnels d'une structure par son ID
    @Query("SELECT s.personnels FROM Structure s WHERE s.id = :structureId")
    List<Utilisateur> findPersonnelsByStructureId(@Param("structureId") Long structureId);
}
