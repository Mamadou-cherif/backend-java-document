package com.smshub.org.features.bureau.dtos;

import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BureauDto {
    // ajout d'ici
    private int id;
    private String name;
    private int directionId;
    private UtilisateurDto responsable;
    private String adresse;
    List<Utilisateur> personnel;
}
