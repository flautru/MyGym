package com.fabien.equipment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {

    private Long id;
    private String label;
    private Set<String> equipmentNames = new HashSet<>();
}
