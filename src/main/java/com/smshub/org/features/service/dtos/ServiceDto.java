package com.smshub.org.features.service.dtos;

import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceDto {
    private int id;
    private String name;
    private UtilisateurDto responsable;
    private String adresse;
    List<Utilisateur> personnel;
}
