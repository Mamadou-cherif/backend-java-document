package com.smshub.org.features.bureau.controller;
import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.bureau.commands.CreateCommand;
import com.smshub.org.features.bureau.commands.UpdateCommand;
import com.smshub.org.features.bureau.converter.BureauConverter;
import com.smshub.org.features.bureau.dtos.BureauDto;
import com.smshub.org.features.bureau.model.Bureau;
import com.smshub.org.features.bureau.service.BureauService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/bureau")
public class BureauController {
    @Autowired
    private final BureauService bureauService;
    private final BureauConverter bureauConverter;


    @PostMapping
    public ApiResponse<BureauDto> create(@RequestBody @Validated CreateCommand createServiceCommand){
        return ApiResponse. created(
                this.bureauConverter.convert(this.bureauService.create(this.bureauConverter.create(createServiceCommand))),
                "Bureau crée avec succès"
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<BureauDto> get(@PathVariable int id){
        return ApiResponse.success(
                this.bureauConverter.convert(this.bureauService.get(id)),
                String.format("Bureau de ID: %s", id)
        );
    }

    @GetMapping
    public ApiResponse<List<BureauDto>> get() {
        List<BureauDto> bureau = this.bureauService.all()
                .stream()
                .map(this.bureauConverter::convert)
                .toList();
        return ApiResponse.success(bureau, "");
    }


    @PutMapping("/{id}")
    public ApiResponse<BureauDto> update(@PathVariable int id, @RequestBody UpdateCommand updateOrganizationCommand) throws ChangeSetPersister.NotFoundException {
        return ApiResponse.success(
                this.bureauConverter.convert(this.bureauService.update(id,this.bureauConverter.update(updateOrganizationCommand))),
                String.format("Bureau de ID est mis a jour avec succès: %s", id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id){
        Bureau bureau = this.bureauService.get(id);
        this.bureauService.delete(bureau);
        return ApiResponse.success(
                String.format("Service de ID : %s supprimé avec succès", id)
        );
    }
}
