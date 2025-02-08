package com.smshub.org.features.utilisateur.commands;

public record CreateCommand(
        String fullName,
        String email,
        String telephone,
        String description
) {}
