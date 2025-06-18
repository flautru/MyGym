package com.fabien.equipment_services.repository;

import com.fabien.equipment_services.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
