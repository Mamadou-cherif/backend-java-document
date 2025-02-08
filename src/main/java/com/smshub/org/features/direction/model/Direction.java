package com.smshub.org.features.direction.model;

import com.smshub.org.core.entities.BaseEntity;
import com.smshub.org.features.service.model.Services;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "direction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Direction extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Utilisateur responsable;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Services service;

    @ManyToMany
    @JoinTable(
            name = "direction_personnels", // Table de jointure explicite
            joinColumns = @JoinColumn(name = "direction_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private List<Utilisateur> personnels;

    @Column(length = 255)
    private String adresse;

}
