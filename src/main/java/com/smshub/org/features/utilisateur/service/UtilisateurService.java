package com.smshub.org.features.utilisateur.service;

import com.smshub.org.features.utilisateur.commands.CreateCommand;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface UtilisateurService {
    Utilisateur create(Utilisateur typeDocument);
    List<Utilisateur> all();
    public Utilisateur update(int id, Utilisateur typeDocument) throws ChangeSetPersister.NotFoundException;


    List<Utilisateur> create(List<Utilisateur> utilisateurs) throws DataIntegrityViolationException;

    //List<Utilisateur> getUserInStructure(int structure_id) throws DataIntegrityViolationException;


    public Utilisateur get(int id);
    public void delete(Utilisateur typeDocument);
}
