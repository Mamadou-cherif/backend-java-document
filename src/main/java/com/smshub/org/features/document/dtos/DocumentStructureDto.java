package com.smshub.org.features.document.dtos;

import com.smshub.org.features.structure.dtos.StructureDto;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.typedocument.model.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentStructureDto {
    private int id;
    private String title;
    private String description;
    private String author;
    private Structure structure;
    private TypeDocument typeDocument;
}
