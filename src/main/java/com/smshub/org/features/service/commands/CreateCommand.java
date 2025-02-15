package com.smshub.org.features.service.commands;

import java.util.List;

public record CreateCommand(
        String name,
        int responsable,
        int structureId,
        List<Integer> personnel,
        String adresse
) {}
