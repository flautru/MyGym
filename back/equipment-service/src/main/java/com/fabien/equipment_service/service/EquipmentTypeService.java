package com.fabien.equipment_service.service;

import com.fabien.equipment_service.model.EquipmentType;

import java.util.List;
import java.util.Optional;

public interface EquipmentTypeService {

    List<EquipmentType> getAllEquipmentsType();

    Optional<EquipmentType> getEquipmentTypeById(Long id);

    EquipmentType createEquipmentType(EquipmentType equipmentType);

    EquipmentType updateEquipmentType(EquipmentType equipmentType);

    void deleteEquipmentType(Long id);
}
