package com.smshub.org.features.structure.commands;

import com.smshub.org.features.utilisateur.model.Utilisateur;

import java.util.List;

public record CreateCommand(
        String name,
        int responsable,
        List<Integer> personnel,
        String adresse
) {}
