package com.smshub.org.features.document.dtos;
import com.smshub.org.features.typedocument.model.TypeDocument;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private int id;
    private String title;
    private String description;
    private String author;
    private TypeDocument typeDocument;
}
