package com.smshub.org.features.document.controller;

import com.smshub.org.core.helpers.response.ApiResponse;
import com.smshub.org.features.document.commands.CreatDocumentCommand;
import com.smshub.org.features.document.commands.UpdateDocumentCommand;
import com.smshub.org.features.document.converter.DocumentConverter;
import com.smshub.org.features.document.dtos.DocumentDto;
import com.smshub.org.features.document.dtos.DocumentListDto;
import com.smshub.org.features.document.model.Document;
import com.smshub.org.features.document.service.DocumentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/document")
@ControllerAdvice
@RestController
@Transactional
public class DocumentController {
    private final DocumentConverter documentConverter;
    private final DocumentService documentService;
    public static class Obj{
        public String title;
    }
    @Autowired
    public DocumentController(DocumentConverter documentConverter, DocumentService documentService) {
        this.documentConverter = documentConverter;
        this.documentService = documentService;
    }

    @PostMapping
    public ApiResponse<DocumentDto> addDocument(@RequestBody CreatDocumentCommand document) {
        return ApiResponse.success(
                DocumentConverter.convert(this.documentService.add(this.documentConverter.create(document)))
                ,
                "L'ajout effectué avec succès"

        );
    }


    @GetMapping("/containing")
    public ApiResponse<List<DocumentListDto>> getDocumentContaing(@RequestBody Obj obj) {
        List<DocumentListDto> doc = this.documentService.findByTitleContaining(obj.title)
                .stream()
                .map(DocumentConverter::convertListe)
                .toList();
        return ApiResponse.success(
                doc
                ,
                "Liste des documents contenants"

        );
    }



    @PutMapping("/{id}")
    public ApiResponse<DocumentDto> updateDocument(@PathVariable int id,@RequestBody UpdateDocumentCommand updateDocument) {
        return ApiResponse.success(
                DocumentConverter.convert(this.documentService.update(id,this.documentConverter.update(updateDocument)))
                ,
                "La modification est effectuée avec succès"
        );
    }

    @GetMapping
    public ApiResponse<List<DocumentListDto>> getAllDocuments() {
        List<DocumentListDto> documents = this.documentService.all()
                .stream()
                .map(DocumentConverter::convertListe)
                .toList();
        return ApiResponse.success(
                documents, ""
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<DocumentListDto> getDocument(@PathVariable int id) {
       Optional<Document> documents = this.documentService.get(id);
        DocumentListDto doc = DocumentConverter.convertListe(documents.get());
        return ApiResponse.success(
                doc, ""
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Document> deleteDocument(@PathVariable int id) {
        Optional<Document> documents = this.documentService.get(id);
        if(documents.isPresent()) {
            this.documentService.delete(id);
        }
        return ApiResponse.success(
                "Suppression effectuée avec succès, bravo"
        );
    }

}
