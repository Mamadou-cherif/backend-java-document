package com.smshub.org.features.utilisateur.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto {
    private int id;
    private String fullName;
    private String email;
    private String telephone;
    private String description;
}
