package com.smshub.org.features.utilisateur.model;

import com.smshub.org.core.entities.BaseEntity;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.structure.model.Structure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "personnels")
    private List<Structure> structures= new ArrayList<>();

    @ManyToMany(mappedBy = "personnels")
    private List<Services> services = new ArrayList<>();
}
