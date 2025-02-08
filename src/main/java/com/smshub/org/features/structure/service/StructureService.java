package com.smshub.org.features.structure.service;

import com.smshub.org.features.structure.model.Structure;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface StructureService {
    Structure create(Structure structure);
    List<Structure> all();
    public Structure update(int id, Structure structure) throws ChangeSetPersister.NotFoundException;
    public void create(List<Structure> structures) throws ChangeSetPersister.NotFoundException;
    public Structure get(int id);
    public void delete(Structure structure);
}
