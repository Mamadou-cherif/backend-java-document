package com.smshub.org.features.direction.converter;

import com.smshub.org.features.direction.commands.CreateCommand;
import com.smshub.org.features.direction.commands.UpdateCommand;
import com.smshub.org.features.direction.dtos.DirectionDto;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.service.repository.ServiceRepository;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DirectionConverter {
    private final UtilisateurRepository utilisateurRepository;
    private final ServiceRepository serviceRepository;

    public DirectionDto convert(Direction direction) {
        return DirectionDto
                .builder()
                .id(direction.getId())
                .responsable(convertUtilisateurToDto(direction.getResponsable())) // Convertir le responsable
                .name(direction.getName())
                .adresse(direction.getAdresse())
                .personnel(direction.getPersonnels())
                .build();
    }

    private UtilisateurDto convertUtilisateurToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .fullName(utilisateur.getFullName()) // Assurez-vous que `nom` existe dans `Utilisateur`
                .telephone(utilisateur.getTelephone())
                .build();
    }


    public Direction create(CreateCommand createCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(createCommand.responsable());
        Optional<Services> service= this.serviceRepository.findById(createCommand.serviceId());

        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(createCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Direction
                .builder()
                .responsable(user.get())
                .name(createCommand.name())
                .service(service.get())
                .adresse(createCommand.adresse())
                .personnels(utilisateurs)
                .createdAt(LocalDateTime.now())
                .build();
    }

    List<Utilisateur> getUtilisateurByArrayInArgument(List<Integer> array){
        List<Utilisateur> users = new ArrayList<>();

        array.forEach(a->{
            users.add(utilisateurRepository.findById(a).orElse(null));
        });
        return users;
    }

    public Direction update(UpdateCommand updateCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(updateCommand.responsable());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(updateCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Direction
                .builder()
                .id(updateCommand.id())
                .responsable(user.get())
                .name(updateCommand.name())
                .adresse(updateCommand.adresse())
                .personnels(utilisateurs)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

