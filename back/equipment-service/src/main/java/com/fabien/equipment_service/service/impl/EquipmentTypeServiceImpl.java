package com.fabien.equipment_service.service.impl;

import com.fabien.equipment_service.model.EquipmentType;
import com.fabien.equipment_service.repository.EquipmentTypeRepository;
import com.fabien.equipment_service.service.EquipmentTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;

    @Override
    public List<EquipmentType> getAllEquipmentsType() {
        return equipmentTypeRepository.findAll();
    }

    @Override
    public Optional<EquipmentType> getEquipmentTypeById(Long id) {
        return equipmentTypeRepository.findById(id);
    }

    @Override
    @Transactional
    public EquipmentType createEquipmentType(EquipmentType equipmentType) {
        return equipmentTypeRepository.save(equipmentType);
    }

    @Override
    @Transactional
    public EquipmentType updateEquipmentType(EquipmentType equipmentType) {
        if (equipmentType.getId() == null) {
            throw new IllegalArgumentException("EquipmentType ID cannot be null for update");
        }

        if (!equipmentTypeRepository.existsById(equipmentType.getId())) {
            throw new EntityNotFoundException("EquipmentType not found with id: " + equipmentType.getId());
        }

        return equipmentTypeRepository.save(equipmentType);
    }

    @Override
    @Transactional
    public void deleteEquipmentType(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!equipmentTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("EquipmentType not found with id: " + id);
        }

        equipmentTypeRepository.deleteById(id);
    }
}