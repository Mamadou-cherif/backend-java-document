package com.smshub.org.features.direction.commands;

import java.util.List;

public record CreateCommand(
        String name,
        int responsable,
        List<Integer> personnel,
        String adresse
) {}
