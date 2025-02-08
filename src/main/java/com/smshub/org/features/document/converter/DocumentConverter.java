package com.smshub.org.features.document.converter;

import com.smshub.org.features.document.commands.CreatDocumentCommand;
import com.smshub.org.features.document.commands.UpdateDocumentCommand;
import com.smshub.org.features.document.dtos.DocumentDto;
import com.smshub.org.features.document.dtos.DocumentListDto;
import com.smshub.org.features.document.model.Document;
import com.smshub.org.features.typedocument.model.TypeDocument;
import com.smshub.org.features.typedocument.repository.TypeDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class DocumentConverter {
    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    public Document create(CreatDocumentCommand createDocumentCommand) {

        TypeDocument typeDocument = this.typeDocumentRepository.findById(createDocumentCommand.typeDocId())
                .orElseThrow(() -> new RuntimeException("TypeDocument non trouvé avec l'ID : " + createDocumentCommand.typeDocId()));
        // Construire et retourner le Document
        return Document
                .builder()
                    .title(createDocumentCommand.title())
                    .description(createDocumentCommand.description())
                    .author(createDocumentCommand.author())
                    .typeDocument(typeDocument) // Associer le TypeDocument récupéré
                .build();
    }

    public Document update(UpdateDocumentCommand updateDocumentCommand) {

        TypeDocument typeDocument = this.typeDocumentRepository.findById(updateDocumentCommand.typeDocId())
                .orElseThrow(() -> new RuntimeException("TypeDocument non trouvé avec l'ID : " + updateDocumentCommand.typeDocId()));
        // Construire et retourner le Document
        return Document
                .builder()
                .id(updateDocumentCommand.id())
                .title(updateDocumentCommand.title())
                .description(updateDocumentCommand.description())
                .author(updateDocumentCommand.author())
                .typeDocument(typeDocument) // Associer le TypeDocument récupéré
                .build();
    }



    public static DocumentDto convert(Document document) {
        return DocumentDto
                .builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .author(document.getAuthor())
                .typeDocument(document.getTypeDocument())
                .build();
    }


    public static DocumentListDto convertListe(Document document) {
        String typeDocumentLibelle = null;
        if (document.getTypeDocument() != null) {
            typeDocumentLibelle = document.getTypeDocument().getLibelle();
        }

        return DocumentListDto
                .builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .author(document.getAuthor())
                .typeDocument(typeDocumentLibelle)
                .build();
    }

}
