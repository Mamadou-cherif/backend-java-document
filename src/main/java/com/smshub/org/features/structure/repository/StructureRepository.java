package com.smshub.org.features.structure.repository;


import com.smshub.org.features.structure.model.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Integer> {
}
