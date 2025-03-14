package com.smshub.org.features.bureau.model;

import com.smshub.org.core.entities.BaseEntity;
import com.smshub.org.features.direction.model.Direction;
import com.smshub.org.features.utilisateur.model.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "bureau")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Bureau extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Utilisateur responsable;

    @ManyToOne
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;

    @ManyToMany
    @JoinTable(
            name = "bureau_personnels", // tTable de jointure explicite
            joinColumns = @JoinColumn(name = "bureau_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private List<Utilisateur> personnels;

    @Column(length = 255)
    private String adresse;

}
