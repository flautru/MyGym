package com.fabien.equipment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {

    private String label;
    private Set<Long> equipmentsIds = new HashSet<>();
}
