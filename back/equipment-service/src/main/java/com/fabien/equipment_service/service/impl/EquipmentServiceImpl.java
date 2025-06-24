package com.fabien.equipment_service.service.impl;

import com.fabien.equipment_service.model.Equipment;
import com.fabien.equipment_service.repository.EquipmentRepository;
import com.fabien.equipment_service.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipementRepository;

    public List<Equipment> getAllEquipment() {
        return equipementRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        return equipementRepository.findById(id);
    }

    public Equipment saveEquipment(Equipment equipements) {
        return equipementRepository.save(equipements);
    }

    public void deleteEquipment(Long id) {
        equipementRepository.deleteById(id);
    }
}