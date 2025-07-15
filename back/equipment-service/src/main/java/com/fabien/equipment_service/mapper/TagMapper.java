package com.fabien.equipment_service.mapper;

import com.fabien.equipment_service.dto.TagRequest;
import com.fabien.equipment_service.dto.TagResponse;
import com.fabien.equipment_service.model.Equipment;
import com.fabien.equipment_service.model.Tag;
import com.fabien.equipment_service.repository.EquipmentRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TagMapper {

    @Autowired
    protected EquipmentRepository equipmentRepository;

    // -------- TagRequest -> Tag --------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipments", ignore = true)
    public abstract Tag toEntity(TagRequest request);

    @AfterMapping
    protected void mapEquipments(TagRequest request, @MappingTarget Tag tag) {
        if (request.getEquipmentsIds() != null && !request.getEquipmentsIds().isEmpty()) {
            Set<Equipment> equipments = new HashSet<>(equipmentRepository.findAllById(request.getEquipmentsIds()));
            tag.setEquipments(equipments);
        } else {
            tag.setEquipments(new HashSet<>());
        }
    }

    // -------- Tag -> TagResponse --------
    @Mapping(target = "equipmentNames", expression = "java(getEquipmentNames(tag))")
    public abstract TagResponse toResponse(Tag tag);

    protected Set<String> getEquipmentNames(Tag tag) {
        if (tag.getEquipments() == null)
            return new HashSet<>();
        return tag.getEquipments().stream()
                .map(Equipment::getNom)
                .collect(Collectors.toSet());
    }

    public abstract Set<TagResponse> toResponseSet(Set<Tag> tags);
}
