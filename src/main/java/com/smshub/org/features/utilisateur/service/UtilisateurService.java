package com.smshub.org.features.utilisateur.service;

import com.smshub.org.features.utilisateur.model.Utilisateur;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UtilisateurService {
    Utilisateur create(Utilisateur typeDocument);
    List<Utilisateur> all();
    public Utilisateur update(int id, Utilisateur typeDocument) throws ChangeSetPersister.NotFoundException;
    public void create(List<Utilisateur> typeDocuments) throws ChangeSetPersister.NotFoundException;
    public Utilisateur get(int id);
    public void delete(Utilisateur typeDocument);
}
