package com.fabien.equipment_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "equipement_types")
public class EquipmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;

}
