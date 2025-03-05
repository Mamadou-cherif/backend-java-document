package com.smshub.org.features.structure.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.structure.repository.StructureRepository;
import com.smshub.org.features.structure.service.StructureService;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;

    public StructureServiceImpl(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    @Override
    public Structure create(Structure structure) {
        return this.structureRepository.save(structure);
    }

    @Async
    @Override
    public void create(List<Structure> structures) throws DataIntegrityViolationException {
        this.structureRepository.saveAll(structures);
    }

    @Override
    public Structure get(int id) {
        return this.structureRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Structure", "id", id));
    }

    @Override
    public List<Structure> all() {
        return this.structureRepository.findAll();
    }

    @Override
    public Structure update(int id, Structure structure) {
        return this.structureRepository.save(structure);
    }

    @Override
    public void delete(Structure structure) {
        this.structureRepository.delete(structure);
    }

    public List<Utilisateur> getPersonnelsByStructure(Long structureId) {
        return structureRepository.findPersonnelsByStructureId(structureId);
    }
}
