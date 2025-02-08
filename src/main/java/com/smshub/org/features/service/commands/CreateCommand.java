package com.smshub.org.features.service.commands;

import java.util.List;

public record CreateCommand(
        String name,
        int responsable,
        List<Integer> personnel,
        String adresse
) {}
