package com.smshub.org.features.structure.service;

import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface StructureService {
    Structure create(Structure structure);
    List<Structure> all();
    public Structure update(int id, Structure structure) throws ChangeSetPersister.NotFoundException;
    public void create(List<Structure> structures) throws ChangeSetPersister.NotFoundException;
    public Structure get(int id);
    public void delete(Structure structure);
    public List<Utilisateur> getPersonnelsByStructure(Long structureId);
}
