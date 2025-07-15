package com.fabien.equipment_service.controller;

import com.fabien.equipment_service.model.EquipmentType;
import com.fabien.equipment_service.service.EquipmentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EquipmentTypeController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class EquipmentTypeController {

    protected static final String BASE_URL = "/api/equipments_types";
    private final EquipmentTypeService equipmentTypeService;

    @GetMapping
    public ResponseEntity<List<EquipmentType>> getAllEquipmentTypes() {
        log.debug("GET {} - Récupération de tous les EquipmentTypes", BASE_URL);
        List<EquipmentType> equipmentTypes = equipmentTypeService.getAllEquipmentsType();
        log.debug("Récupération réussie : {} equipmentTypes trouvés", equipmentTypes.size());
        return ResponseEntity.ok(equipmentTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentType> getEquipmentTypeById(@PathVariable Long id) {
        log.debug("GET {} /{} - Récupération de equipmentType", BASE_URL, id);
        return equipmentTypeService.getEquipmentTypeById(id)
                .map(equipmentType -> {
                    log.debug("EquipmentType trouvé : {} ({})", equipmentType.getLabel(), equipmentType.getId());
                    return ResponseEntity.ok(equipmentType);
                })
                .orElseGet(() -> {
                    log.warn("EquipmentType non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<EquipmentType> createEquipmentType(@Valid @RequestBody EquipmentType equipmentType) {
        log.debug("POST {} - Création d'un nouveau equipmentType : {}", BASE_URL, equipmentType.getLabel());
        EquipmentType created = equipmentTypeService.createEquipmentType(equipmentType);

        log.info("EquipmentType créé avec succès : {} (ID = {})", equipmentType.getLabel(), equipmentType.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentType> updateEquipmentType(
            @PathVariable Long id,
            @Valid @RequestBody EquipmentType equipmentType) {

        log.debug("PUT {}/{} - Mise à jour de equipmentType", BASE_URL, id);
        equipmentType.setId(id);
        EquipmentType updated = equipmentTypeService.updateEquipmentType(equipmentType);
        log.info("EquipmentType mis à jour avec succès : {} (ID = {})", updated.getLabel(), id);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentType(@PathVariable Long id) {
        log.debug("DELETE {}/{} - Suppression du equipmentType", BASE_URL, id);
        equipmentTypeService.deleteEquipmentType(id);
        log.info("EquipmentType supprimé avec succès : ID = {}", id);
        return ResponseEntity.noContent().build();
    }
}