package com.fabien.equipment_service.repository;

import com.fabien.equipment_service.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
