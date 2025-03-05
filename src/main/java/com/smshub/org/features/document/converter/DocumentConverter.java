package com.smshub.org.features.document.converter;

import com.smshub.org.features.bureau.model.Bureau;
import com.smshub.org.features.bureau.repository.BureauRepository;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.direction.repository.DirectionRepository;
import com.smshub.org.features.document.commands.CreatDocumentCommand;
import com.smshub.org.features.document.commands.UpdateDocumentCommand;
import com.smshub.org.features.document.dtos.*;
import com.smshub.org.features.document.model.Document;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.service.repository.ServiceRepository;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.structure.repository.StructureRepository;
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
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private BureauRepository bureauRepository;

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


    // Create a document for a structure

    public Document createStructureDocument(CreatDocumentCommand createDocumentCommand) {

        Structure structure = this.structureRepository.findById(createDocumentCommand.structureId())
                .orElseThrow(() -> new RuntimeException("La structure non trouvée avec l'ID : " + createDocumentCommand.structureId()));
        TypeDocument typeDocument = this.typeDocumentRepository.findById(createDocumentCommand.typeDocId())
                .orElseThrow(() -> new RuntimeException("TypeDocument non trouvé avec l'ID : " + createDocumentCommand.typeDocId()));
        // Construire et retourner le Document
        return Document
                .builder()
                .title(createDocumentCommand.title())
                .description(createDocumentCommand.description())
                .author(createDocumentCommand.author())
                .typeDocument(typeDocument) // Associer le TypeDocument récupéré
                .structure(structure)
                .build();
    }
    // Create a document for a service
    public Document createServiceDocument(CreatDocumentCommand createDocumentCommand) {

        Services services = this.serviceRepository.findById(createDocumentCommand.structureId())
                .orElseThrow(() -> new RuntimeException("Le service non trouvé avec l'ID : " + createDocumentCommand.structureId()));
        TypeDocument typeDocument = this.typeDocumentRepository.findById(createDocumentCommand.typeDocId())
                .orElseThrow(() -> new RuntimeException("TypeDocument non trouvé avec l'ID : " + createDocumentCommand.typeDocId()));
        // Construire et retourner le Document
        return Document
                .builder()
                .title(createDocumentCommand.title())
                .description(createDocumentCommand.description())
                .author(createDocumentCommand.author())
                .typeDocument(typeDocument) // Associer le TypeDocument récupéré
                .service(services)
                .build();
    }
    // Create a document for a direction
    public Document createDirectionDocument(CreatDocumentCommand createDocumentCommand) {

        Direction direction = this.directionRepository.findById(createDocumentCommand.directionId())
                .orElseThrow(() -> new RuntimeException("La direction non trouvée avec l'ID : " + createDocumentCommand.structureId()));
        TypeDocument typeDocument = this.typeDocumentRepository.findById(createDocumentCommand.typeDocId())
                .orElseThrow(() -> new RuntimeException("TypeDocument non trouvé avec l'ID : " + createDocumentCommand.typeDocId()));

        // Construire et retourner le Document
        return Document
                .builder()
                .title(createDocumentCommand.title())
                .description(createDocumentCommand.description())
                .author(createDocumentCommand.author())
                .typeDocument(typeDocument) // Associer le TypeDocument récupéré
                .direction(direction)
                .build();
    }
    // Create a document for a bureau
    public Document createBureauDocument(CreatDocumentCommand createDocumentCommand) {

        Bureau bureau = this.bureauRepository.findById(createDocumentCommand.bureauId())
                .orElseThrow(() -> new RuntimeException("Le bureau non trouvé avec l'ID : " + createDocumentCommand.bureauId()));
        TypeDocument typeDocument = this.typeDocumentRepository.findById(createDocumentCommand.typeDocId())
                .orElseThrow(() -> new RuntimeException("TypeDocument non trouvé avec l'ID : " + createDocumentCommand.typeDocId()));

        // Construire et retourner le Document
        return Document
                .builder()
                .title(createDocumentCommand.title())
                .description(createDocumentCommand.description())
                .author(createDocumentCommand.author())
                .typeDocument(typeDocument) // Associer le TypeDocument récupéré
                .bureau(bureau)
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

    public static DocumentStructureDto convertStructureDocument(Document document) {
        return DocumentStructureDto
                .builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .author(document.getAuthor())
                .structure(document.getStructure())
                .typeDocument(document.getTypeDocument())
                .build();
    }

    public static DocumentServiceDto convertServiceDocument(Document document) {
        return DocumentServiceDto
                .builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .author(document.getAuthor())
                .service(document.getService())
                .typeDocument(document.getTypeDocument())
                .build();
    }

    public static DocumentDirectionDto convertDirectionDocument(Document document) {
        return DocumentDirectionDto
                .builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .author(document.getAuthor())
                .direction(document.getDirection())
                .typeDocument(document.getTypeDocument())
                .build();
    }

    public static DocumentBureauDto convertBureauDocument(Document document) {
        return DocumentBureauDto
                .builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .author(document.getAuthor())
                .bureau(document.getBureau())
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
