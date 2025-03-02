package com.smshub.org.features.service.model;

import com.smshub.org.core.entities.BaseEntity;
import com.smshub.org.features.structure.model.Structure;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Services extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Utilisateur responsable;

    @ManyToOne
    @JoinColumn(name = "structure_id", nullable = false)
    private Structure structure;

    @ManyToMany
    @JoinTable(
            name = "service_personnels",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private List<Utilisateur> personnels;

    @Column(length = 255)
    private String adresse;

}
