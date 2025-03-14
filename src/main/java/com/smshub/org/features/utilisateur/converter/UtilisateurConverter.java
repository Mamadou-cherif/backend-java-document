package com.smshub.org.features.utilisateur.converter;

import com.smshub.org.features.utilisateur.commands.CreateCommand;
import com.smshub.org.features.utilisateur.commands.UpdateCommand;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class UtilisateurConverter {

    public  UtilisateurDto convert(Utilisateur utilisateur) {
        return UtilisateurDto
                .builder()
                .id(utilisateur.getId())
                .fullName(utilisateur.getFullName())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .build();
    }

    public List<UtilisateurDto> convert1(List<Utilisateur> utilisateur) {
        return utilisateur.stream()
                .map(this::convert)
                .toList();
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

    public List<Utilisateur> create(List<CreateCommand> createCommand){
        List<Utilisateur> utilisateurs;
        utilisateurs =  createCommand.stream()
                .map(this::create)
                .toList();
        return utilisateurs;
    }

    public List<Utilisateur> createf(List<CreateCommand> createCommand){

        List<Utilisateur> utilisateurs = (List<Utilisateur>) createCommand.stream()
                .map(this::create)
                .toList();

       return utilisateurs;
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

