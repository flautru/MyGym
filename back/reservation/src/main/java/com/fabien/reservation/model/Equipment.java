package com.fabien.reservation.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment {

    private Long id;
    private String nom;
    private Integer statut;
}
