package com.smshub.org.features.typedocument.controller;
import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.typedocument.commands.CreateCommand;
import com.smshub.org.features.typedocument.commands.UpdateCommand;
import com.smshub.org.features.typedocument.converter.TypeDocumentConverter;
import com.smshub.org.features.typedocument.dtos.TypeDocumentDto;
import com.smshub.org.features.typedocument.model.TypeDocument;
import com.smshub.org.features.typedocument.service.TypeDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@ControllerAdvice
@RestController
@AllArgsConstructor
@RequestMapping("/v1/typedoc")
public class TypeDocumentController {
    @Autowired
    private final TypeDocumentService typeDocumentService;
    private final TypeDocumentConverter typeDocumentConverter;


    @PostMapping
    public ApiResponse<TypeDocumentDto> create(@RequestBody @Validated CreateCommand createTypeDocumentCommand){
        return ApiResponse. created(
                this.typeDocumentConverter.convert(this.typeDocumentService.create(this.typeDocumentConverter.create(createTypeDocumentCommand))),
                "TypeDocument crée avec succès"
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<TypeDocumentDto> get(@PathVariable int id){
        return ApiResponse.success(
                this.typeDocumentConverter.convert(this.typeDocumentService.get(id)),
                String.format("TypeDocument de ID: %s", id)
        );
    }

    //    public ApiResponse<TypeDocumentDto> get(){
    //        //  this.typeDocumentConverter.convert(this.typeDocumentService.all()),
    //        List<TypeDocumentDto> typeDocument = this.typeDocumentService.all()
    //                .stream()
    //                .map(this.typeDocumentConverter::convert)
    //                .toList();
    //        return ApiResponse.success(typeDocument, "");
    // }

    @GetMapping
    public ApiResponse<List<TypeDocumentDto>> get() {
        List<TypeDocumentDto> typeDocument = this.typeDocumentService.all()
                .stream()
                .map(this.typeDocumentConverter::convert)
                .toList();
        return ApiResponse.success(typeDocument, "");
    }


    @PutMapping("/{id}")
    public ApiResponse<TypeDocumentDto> update(@PathVariable int id, @RequestBody UpdateCommand updateOrganizationCommand) throws ChangeSetPersister.NotFoundException {
        return ApiResponse.success(
                this.typeDocumentConverter.convert(this.typeDocumentService.update(id,this.typeDocumentConverter.update(updateOrganizationCommand))),
                String.format("TypeDocument de ID est mis a jour avec succès: %s", id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id){
        TypeDocument typeDocument = this.typeDocumentService.get(id);
        this.typeDocumentService.delete(typeDocument);
        return ApiResponse.success(
                String.format("TypeDocument de ID : %s supprimé avec succès", id)
        );
    }
}
