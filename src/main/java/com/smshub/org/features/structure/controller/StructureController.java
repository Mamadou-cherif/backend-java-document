package com.smshub.org.features.structure.controller;
import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.structure.commands.CreateCommand;
import com.smshub.org.features.structure.commands.UpdateCommand;
import com.smshub.org.features.structure.converter.StructureConverter;
import com.smshub.org.features.structure.dtos.StructureDto;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.structure.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/structure")
public class StructureController {
    @Autowired
    private final StructureService structureService;
    private final StructureConverter structureConverter;


    @PostMapping
    public ApiResponse<StructureDto> create(@RequestBody @Validated CreateCommand createServiceCommand){
        return ApiResponse. created(
                this.structureConverter.convert(this.structureService.create(this.structureConverter.create(createServiceCommand))),
                "Structure crée avec succès"
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<StructureDto> get(@PathVariable int id){
        return ApiResponse.success(
                this.structureConverter.convert(this.structureService.get(id)),
                String.format("Structure de ID: %s", id)
        );
    }

    @GetMapping
    public ApiResponse<List<StructureDto>> get() {
        List<StructureDto> structure = this.structureService.all()
                .stream()
                .map(this.structureConverter::convert)
                .toList();
        return ApiResponse.success(structure, "");
    }


    @PutMapping("/{id}")
    public ApiResponse<StructureDto> update(@PathVariable int id, @RequestBody UpdateCommand updateOrganizationCommand) throws ChangeSetPersister.NotFoundException {
        return ApiResponse.success(
                this.structureConverter.convert(this.structureService.update(id,this.structureConverter.update(updateOrganizationCommand))),
                String.format("Structure de ID est mis a jour avec succès: %s", id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id){
        Structure structure = this.structureService.get(id);
        this.structureService.delete(structure);
        return ApiResponse.success(
                String.format("Service de ID : %s supprimé avec succès", id)
        );
    }
}
