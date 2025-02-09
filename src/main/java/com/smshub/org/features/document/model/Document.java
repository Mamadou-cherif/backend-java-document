package com.smshub.org.features.document.model;

import com.smshub.org.core.entities.BaseEntity;
import com.smshub.org.features.bureau.model.Bureau;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.structure.model.Structure;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "structure_id", referencedColumnName = "id")
    private Structure structure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Services service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id", referencedColumnName = "id")
    private Direction direction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bureau_id", referencedColumnName = "id")
    private Bureau bureau;


    @Lob
    private Blob thumbnail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_document_id", referencedColumnName = "id")
    private TypeDocument typeDocument;
}
