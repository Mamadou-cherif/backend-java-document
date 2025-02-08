package com.smshub.org.features.utilisateur.service.impl;

import com.smshub.org.core.exceptions.ResourceNotFoundException;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.repository.UtilisateurRepository;
import com.smshub.org.features.utilisateur.service.UtilisateurService;
import com.smshub.org.features.utilisateur.service.UtilisateurService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur create(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }

    @Async
    @Override
    public void create(List<Utilisateur> utilisateurs) throws DataIntegrityViolationException {
        this.utilisateurRepository.saveAll(utilisateurs);
    }

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
