package com.smshub.org.features.utilisateur.controller;
import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.utilisateur.commands.CreateCommand;
import com.smshub.org.features.utilisateur.commands.UpdateCommand;
import com.smshub.org.features.utilisateur.converter.UtilisateurConverter;
import com.smshub.org.features.utilisateur.dtos.UtilisateurDto;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import com.smshub.org.features.utilisateur.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;
    private final UtilisateurConverter utilisateurConverter;




    @PostMapping
    public ApiResponse<UtilisateurDto> create(@RequestBody @Validated CreateCommand createUtilisateurCommand){
        return ApiResponse. created(
                this.utilisateurConverter.convert(this.utilisateurService.create(this.utilisateurConverter.create(createUtilisateurCommand))),
                "Utilisateur crée avec succès"
        );
    }


    @PostMapping("/array")
    public ApiResponse<List<UtilisateurDto>> createMany(@RequestBody @Validated List<CreateCommand> createUtilisateurCommand){
        return ApiResponse. created(
                this.utilisateurConverter.convert1(this.utilisateurService.create(this.utilisateurConverter.create(createUtilisateurCommand))),
                "Utilisateurs crées avec succès many"
        );
    }

 


    @GetMapping("/{id}")
    public ApiResponse<UtilisateurDto> get(@PathVariable int id){
        return ApiResponse.success(
                this.utilisateurConverter.convert(this.utilisateurService.get(id)),
                String.format("Utilisateur de ID: %s", id)
        );
    }

    @GetMapping
    public ApiResponse<List<UtilisateurDto>> get() {
        List<UtilisateurDto> utilisateur = this.utilisateurService.all()
                .stream()
                .map(this.utilisateurConverter::convert)
                .toList();
        return ApiResponse.success(utilisateur, "");
    }

//    @GetMapping("/user/{id}")
//    public ApiResponse<List<UtilisateurDto>> getUserInStructure(@PathVariable int id) {
//        List<UtilisateurDto> utilisateur = this.utilisateurService.getUserInStructure(id)
//                .stream()
//                .map(this.utilisateurConverter::convert)
//                .toList();
//        return ApiResponse.success(utilisateur, "");
//    }

//
//    // Endpoint pour récupérer les utilisateurs d'une structure
//    @GetMapping("/structure/{structureId}")
//    public  ApiResponse<List<UtilisateurDto>>  getUtilisateursByStructureId(@PathVariable int structureId) {
//         List<UtilisateurDto> users = this.utilisateurService.getUtilisateursByStructureId(structureId)
//                 .stream()
//                .map(this.utilisateurConverter::convert)
//                .toList();
//
//        return ApiResponse.success(users, "");
//    }

    @PutMapping("/{id}")
    public ApiResponse<UtilisateurDto> update(@PathVariable int id, @RequestBody UpdateCommand updateOrganizationCommand) throws ChangeSetPersister.NotFoundException {
        return ApiResponse.success(
                this.utilisateurConverter.convert(this.utilisateurService.update(id,this.utilisateurConverter.update(updateOrganizationCommand))),
                String.format("Utilisateur de ID est mis a jour avec succès: %s", id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id){
        Utilisateur utilisateur = this.utilisateurService.get(id);
        this.utilisateurService.delete(utilisateur);
        return ApiResponse.success(
                String.format("Utilisateur de ID : %s supprimé avec succès", id)
        );
    }
}
