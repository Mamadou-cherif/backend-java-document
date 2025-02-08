package com.smshub.org.features.utilisateur.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurListDto {
    private String fullName;
    private String telephone;
}
