package com.smshub.org.features.typedocument.converter;

import com.smshub.org.features.typedocument.commands.CreateCommand;
import com.smshub.org.features.typedocument.commands.UpdateCommand;
import com.smshub.org.features.typedocument.dtos.TypeDocumentDto;
import com.smshub.org.features.typedocument.model.TypeDocument;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TypeDocumentConverter {

    public TypeDocumentDto convert(TypeDocument typeDocument) {
        return TypeDocumentDto
                .builder()
                .id(typeDocument.getId())
                .libelle(typeDocument.getLibelle())
                .description(typeDocument.getDescription())
                .build();
    }

    public TypeDocument create(CreateCommand createCommand){
        return TypeDocument
                .builder()
                .libelle(createCommand.libelle())
                .description(createCommand.description())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TypeDocument update(UpdateCommand updateCommand){
        return TypeDocument
                .builder()
                .id(updateCommand.id())
                .libelle(updateCommand.libelle())
                .description(updateCommand.description())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

