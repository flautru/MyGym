package com.fabien.equipment_services.service;

import com.fabien.equipment_services.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    List<Equipment> getAllEquipment();
    Optional<Equipment> getEquipmentById(Long id);
    Equipment saveEquipment(Equipment equipements);
    void deleteEquipment(Long id);
}