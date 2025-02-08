package com.smshub.org.features.utilisateur.model;

import com.smshub.org.core.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Utilisateur extends BaseEntity {
    private String fullName;
    private String email;
    private String telephone;
    private String description;
    private String password;
}
