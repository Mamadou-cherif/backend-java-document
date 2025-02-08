package com.smshub.org.features.utilisateur.commands;

public record UpdateCommand(
        int id,
        String fullName,
        String email,
        String telephone,
        String description
) {}
