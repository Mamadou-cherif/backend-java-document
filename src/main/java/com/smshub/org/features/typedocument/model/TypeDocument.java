package com.smshub.org.features.typedocument.model;

import com.smshub.org.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "typedocument")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TypeDocument extends BaseEntity {
    private String libelle;
    private String description;
}
