package com.smshub.org.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public abstract class BaseEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
