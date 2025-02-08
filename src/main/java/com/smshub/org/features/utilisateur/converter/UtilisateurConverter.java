package com.smshub.org.features.utilisateur.converter;

import com.smshub.org.features.utilisateur.commands.CreateCommand;
import com.smshub.org.features.utilisateur.commands.UpdateCommand;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UtilisateurConverter {

    public UtilisateurDto convert(Utilisateur utilisateur) {
        return UtilisateurDto
                .builder()
                .id(utilisateur.getId())
                .fullName(utilisateur.getFullName())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .build();
    }

    public Utilisateur create(CreateCommand createCommand){
        return Utilisateur
                .builder()
                .fullName(createCommand.fullName())
                .email(createCommand.email())
                .telephone(createCommand.telephone())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Utilisateur update(UpdateCommand updateCommand){
        return Utilisateur
                .builder()
                .id(updateCommand.id())
                .fullName(updateCommand.fullName())
                .email(updateCommand.email())
                .telephone(updateCommand.telephone())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

