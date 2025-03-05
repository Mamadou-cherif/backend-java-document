package com.smshub.org.features.bureau.commands;

import java.util.List;

public record CreateCommand(
        String name,
        int responsable,
        int directionId,
        List<Integer> personnel,
        String adresse
) {}
