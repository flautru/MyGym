package com.fabien.equipment_service.mapper;

import com.fabien.equipment_service.dto.EquipmentTypeRequest;
import com.fabien.equipment_service.dto.EquipmentTypeResponse;
import com.fabien.equipment_service.model.EquipmentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentTypeMapper {

    EquipmentTypeResponse toResponse(EquipmentType equipmentType);

    EquipmentType toEntity(EquipmentTypeRequest equipmentTypeRequest);
}
