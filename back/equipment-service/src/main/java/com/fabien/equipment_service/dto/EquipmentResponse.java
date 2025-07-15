package com.fabien.equipment_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EquipmentResponse {

    private Long id;
    private String nom;
    private String imageUrl;
    private String type;
    private Set<String> tagNames;
    private Integer status;
}