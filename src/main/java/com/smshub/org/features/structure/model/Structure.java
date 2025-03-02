package com.smshub.org.features.structure.model;

import com.smshub.org.core.entities.BaseEntity;
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
@Table(name = "structure")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Structure extends BaseEntity {


    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Utilisateur responsable;

    // Relation Many-to-Many avec Utilisateur (côté inverse)
    @ManyToMany
    @JoinTable(
            name = "structure_personnels", // Table de jointure explicite
            joinColumns = @JoinColumn(name = "structure_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private List<Utilisateur> personnels = new ArrayList<>();

    @Column(length = 255)
    private String adresse;


}
