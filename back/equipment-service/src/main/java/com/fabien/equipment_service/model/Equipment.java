package com.fabien.equipment_service.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "tags")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Equipments")
public class Equipment {

    @ManyToMany
    @JoinTable(
            name = "equipment_tags",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Set<Tag> tags = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private EquipmentType type;
    private Integer status;
}