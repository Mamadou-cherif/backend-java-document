package com.smshub.org.features.bureau.commands;

import java.util.List;

public record UpdateCommand(
        int id,
        String name,
        int responsable,
        List<Integer> personnel,
        String adresse
) {}
