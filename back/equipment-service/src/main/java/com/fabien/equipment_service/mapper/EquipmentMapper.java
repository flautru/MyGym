package com.fabien.equipment_service.mapper;

import com.fabien.equipment_service.dto.EquipmentRequest;
import com.fabien.equipment_service.dto.EquipmentResponse;
import com.fabien.equipment_service.model.Equipment;
import com.fabien.equipment_service.model.EquipmentType;
import com.fabien.equipment_service.model.Tag;
import com.fabien.equipment_service.repository.EquipmentTypeRepository;
import com.fabien.equipment_service.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipmentMapper {

    private final TagRepository tagRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final TagMapper tagMapper; // Si vous en avez besoin

    public EquipmentResponse toResponse(Equipment equipment) {
        if (equipment == null) {
            return null;
        }

        return EquipmentResponse.builder()
                .id(equipment.getId())
                .imageUrl(equipment.getImageUrl())
                .nom(equipment.getNom())
                .type(equipment.getType() != null ? equipment.getType().getLabel() : null)
                .tagNames(equipment.getTags().stream()
                        .map(Tag::getLabel)
                        .collect(Collectors.toSet()))
                .status(equipment.getStatus())
                .build();
    }

    public Equipment toEntity(EquipmentRequest dto) {
        if (dto == null) {
            return null;
        }

        Equipment equipment = new Equipment();
        //equipment.setId(dto.getId());
        equipment.setImageUrl(dto.getImageUrl());
        equipment.setNom(dto.getNom());
        equipment.setType(getTypeFromId(dto.getTypeId()));
        equipment.setTags(getTagsFromIds(dto.getTagIds()));
        equipment.setStatus(dto.getStatus());

        return equipment;
    }

    private EquipmentType getTypeFromId(Long typeId) {
        if (typeId == null) {
            return null;
        }
        return equipmentTypeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid type ID: " + typeId));
    }

    private Set<Tag> getTagsFromIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(tagRepository.findAllById(ids));
    }
}