package com.smshub.org.features.direction.commands;

import java.util.List;

public record CreateCommand(
        String name,
        int responsable,
        int serviceId,
        List<Integer> personnel,
        String adresse
) {}
