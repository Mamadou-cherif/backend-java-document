package com.smshub.org.features.document.dtos;

import com.smshub.org.features.typedocument.model.TypeDocument;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentListDto {
    private int id;
    private String title;
    private String description;
    private String author;
    private String typeDocument;
}
