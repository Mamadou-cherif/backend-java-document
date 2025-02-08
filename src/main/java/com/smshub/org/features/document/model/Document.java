package com.smshub.org.features.document.model;

import com.smshub.org.core.entities.BaseEntity;
import com.smshub.org.features.typedocument.model.TypeDocument;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document extends BaseEntity {
    private String title;
    private String description;
    private String author;
    @Lob
    private Blob thumbnail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_document_id", referencedColumnName = "id")
    private TypeDocument typeDocument;
}
