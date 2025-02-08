package com.smshub.org.features.direction.controller;
import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.direction.commands.CreateCommand;
import com.smshub.org.features.direction.commands.UpdateCommand;
import com.smshub.org.features.direction.converter.DirectionConverter;
import com.smshub.org.features.direction.dtos.DirectionDto;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.direction.service.DirectionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/direction")
public class DirectionController {
    @Autowired
    private final DirectionService directionService;
    private final DirectionConverter directionConverter;


    @PostMapping
    public ApiResponse<DirectionDto> create(@RequestBody @Validated CreateCommand createServiceCommand){
        return ApiResponse. created(
                this.directionConverter.convert(this.directionService.create(this.directionConverter.create(createServiceCommand))),
                "Direction crée avec succès"
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<DirectionDto> get(@PathVariable int id){
        return ApiResponse.success(
                this.directionConverter.convert(this.directionService.get(id)),
                String.format("Direction de ID: %s", id)
        );
    }

    @GetMapping
    public ApiResponse<List<DirectionDto>> get() {
        List<DirectionDto> direction = this.directionService.all()
                .stream()
                .map(this.directionConverter::convert)
                .toList();
        return ApiResponse.success(direction, "");
    }


    @PutMapping("/{id}")
    public ApiResponse<DirectionDto> update(@PathVariable int id, @RequestBody UpdateCommand updateOrganizationCommand) throws ChangeSetPersister.NotFoundException {
        return ApiResponse.success(
                this.directionConverter.convert(this.directionService.update(id,this.directionConverter.update(updateOrganizationCommand))),
                String.format("Direction de ID est mis a jour avec succès: %s", id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id){
        Direction direction = this.directionService.get(id);
        this.directionService.delete(direction);
        return ApiResponse.success(
                String.format("Service de ID : %s supprimé avec succès", id)
        );
    }
}
