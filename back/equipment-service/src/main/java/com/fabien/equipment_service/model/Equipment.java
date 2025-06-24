package com.fabien.equipment_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String imageUrl;
    private String nom;
    private Integer statut;
}