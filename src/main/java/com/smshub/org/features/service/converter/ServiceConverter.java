package com.smshub.org.features.service.converter;

import com.smshub.org.features.service.commands.CreateCommand;
import com.smshub.org.features.service.commands.UpdateCommand;
import com.smshub.org.features.service.dtos.ServiceDto;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.structure.repository.StructureRepository;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ServiceConverter {
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final StructureRepository structureRepository;

    public ServiceDto convert(Services service) {
        return ServiceDto
                .builder()
                .id(service.getId())
                .responsable(convertUtilisateurToDto(service.getResponsable())) // Convertir le responsable
                .name(service.getName())
                .structure(service.getStructure().getName())
                .adresse(service.getAdresse())
                .personnel(service.getPersonnels())
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


    public Services create(CreateCommand createCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(createCommand.responsable());
        Optional<Structure> structure= this.structureRepository.findById(createCommand.structureId());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(createCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Services
                .builder()
                .responsable(user.get())
                .name(createCommand.name())
                .adresse(createCommand.adresse())
                .structure(structure.get())
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

    public Services update(UpdateCommand updateCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(updateCommand.responsable());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(updateCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Services
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

