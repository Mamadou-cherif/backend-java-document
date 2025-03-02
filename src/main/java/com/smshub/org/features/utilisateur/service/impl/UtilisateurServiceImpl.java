package com.smshub.org.features.utilisateur.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.structure.repository.StructureRepository;
import com.smshub.org.features.utilisateur.commands.CreateCommand;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.repository.UtilisateurRepository;
import com.smshub.org.features.utilisateur.service.UtilisateurService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final StructureRepository structureRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, StructureRepository structureRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.structureRepository = structureRepository;
    }

    @Override
    public Utilisateur create(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> create(List<Utilisateur> utilisateurs) throws DataIntegrityViolationException {
       return this.utilisateurRepository.saveAll(utilisateurs);
    }


//    @Override
//    public List<Utilisateur> getUserInStructure(int structure_id) throws DataIntegrityViolationException {
//        return this.structureRepository.findByStructures_Id(structure_id);
//    }
    @Override
    public Utilisateur get(int id) {
        return this.utilisateurRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Utilisateur", "id", id));
    }



    @Override
    public List<Utilisateur> all() {
        return this.utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur update(int id, Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }



    @Override
    public void delete(Utilisateur utilisateur) {
        this.utilisateurRepository.delete(utilisateur);
    }

}
