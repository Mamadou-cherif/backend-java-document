package com.smshub.org.features.typedocument.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeDocumentDto {
    private int id;
    private String libelle;
    private String description;
}
