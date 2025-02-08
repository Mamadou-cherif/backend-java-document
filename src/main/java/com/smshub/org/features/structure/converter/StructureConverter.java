package com.smshub.org.features.structure.converter;

import com.smshub.org.features.structure.commands.CreateCommand;
import com.smshub.org.features.structure.commands.UpdateCommand;
import com.smshub.org.features.structure.dtos.StructureDto;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.structure.service.StructureService;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.repository.UtilisateurRepository;
import com.smshub.org.features.utilisateur.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class StructureConverter {
    private final UtilisateurRepository utilisateurRepository;

    public StructureDto convert(Structure structure) {
        return StructureDto
                .builder()
                .id(structure.getId())
                .responsable(convertUtilisateurToDto(structure.getResponsable())) // Convertir le responsable
                .name(structure.getName())
                .adresse(structure.getAdresse())
                .personnel(structure.getPersonnels())
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


    public Structure create(CreateCommand createCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(createCommand.responsable());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(createCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Structure
                .builder()
                .responsable(user.get())
                .name(createCommand.name())
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

    public Structure update(UpdateCommand updateCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(updateCommand.responsable());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(updateCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Structure
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

