package com.fabien.equipment_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EquipmentRequest {

    private String imageUrl;
    private String nom;
    @JsonProperty("type")
    private Long typeId;
    @JsonProperty("tags")
    private Set<Long> tagIds;
    private Integer status;
}
