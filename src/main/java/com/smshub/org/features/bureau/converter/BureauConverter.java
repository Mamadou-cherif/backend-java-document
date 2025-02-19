package com.smshub.org.features.bureau.converter;

import com.smshub.org.features.bureau.commands.CreateCommand;
import com.smshub.org.features.bureau.commands.UpdateCommand;
import com.smshub.org.features.bureau.dtos.BureauDto;
import com.smshub.org.features.bureau.model.Bureau;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.direction.repository.DirectionRepository;
import com.smshub.org.features.structure.model.Structure;
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
public class BureauConverter {
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final DirectionRepository directionRepository;

    public BureauDto convert(Bureau bureau) {
        return BureauDto
                .builder()
                .id(bureau.getId())
                .responsable(convertUtilisateurToDto(bureau.getResponsable())) // Convertir le responsable
                .name(bureau.getName())
                .directionId(bureau.getDirection().getId())
                .adresse(bureau.getAdresse())
                .personnel(bureau.getPersonnels())
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


    public Bureau create(CreateCommand createCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(createCommand.responsable());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(createCommand.personnel());
        Optional<Direction> direction= this.directionRepository.findById(createCommand.directionId());

        if (user.isEmpty()) {
            return null;
        }

        if(direction.isEmpty()){
            return null;
        }
        return Bureau
                .builder()
                .responsable(user.get())
                .name(createCommand.name())
                .adresse(createCommand.adresse())
                .direction(direction.get())
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

    public Bureau update(UpdateCommand updateCommand){
        Optional<Utilisateur> user= this.utilisateurRepository.findById(updateCommand.responsable());
        List<Utilisateur> utilisateurs = this.getUtilisateurByArrayInArgument(updateCommand.personnel());

        if (user.isEmpty()) {
            return null;
        }
        return Bureau
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

