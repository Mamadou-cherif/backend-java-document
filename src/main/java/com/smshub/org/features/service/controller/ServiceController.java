package com.smshub.org.features.service.controller;
import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.service.commands.CreateCommand;
import com.smshub.org.features.service.commands.UpdateCommand;
import com.smshub.org.features.service.converter.ServiceConverter;
import com.smshub.org.features.service.dtos.ServiceDto;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.service.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private final ServiceService serviceService;
    private final ServiceConverter serviceConverter;


    @PostMapping
    public ApiResponse<ServiceDto> create(@RequestBody @Validated CreateCommand createServiceCommand){
        return ApiResponse. created(
                this.serviceConverter.convert(this.serviceService.create(this.serviceConverter.create(createServiceCommand))),
                "Service crée avec succès"
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<ServiceDto> get(@PathVariable int id){
        return ApiResponse.success(
                this.serviceConverter.convert(this.serviceService.get(id)),
                String.format("Service de ID: %s", id)
        );
    }

    @GetMapping
    public ApiResponse<List<ServiceDto>> get() {
        List<ServiceDto> service = this.serviceService.all()
                .stream()
                .map(this.serviceConverter::convert)
                .toList();
        return ApiResponse.success(service, "");
    }


    @PutMapping("/{id}")
    public ApiResponse<ServiceDto> update(@PathVariable int id, @RequestBody UpdateCommand updateOrganizationCommand) throws ChangeSetPersister.NotFoundException {
        return ApiResponse.success(
                this.serviceConverter.convert(this.serviceService.update(id,this.serviceConverter.update(updateOrganizationCommand))),
                String.format("Service de ID est mis a jour avec succès: %s", id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id){
        Services service = this.serviceService.get(id);
        this.serviceService.delete(service);
        return ApiResponse.success(
                String.format("Service de ID : %s supprimé avec succès", id)
        );
    }
}
