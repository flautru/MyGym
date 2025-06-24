package com.fabien.salle_service.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String adress;
    private String statut;

}
