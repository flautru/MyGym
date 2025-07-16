package com.fabien.equipment_service.controller;

import com.fabien.equipment_service.dto.EquipmentTypeRequest;
import com.fabien.equipment_service.dto.EquipmentTypeResponse;
import com.fabien.equipment_service.mapper.EquipmentTypeMapper;
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

    protected static final String BASE_URL = "/api/equipments/types";
    private final EquipmentTypeService equipmentTypeService;
    private final EquipmentTypeMapper equipmentTypeMapper;

    @GetMapping
    public ResponseEntity<List<EquipmentTypeResponse>> getAllEquipmentTypes() {
        log.debug("GET {} - Récupération de tous les EquipmentTypes", BASE_URL);
        List<EquipmentType> equipmentTypes = equipmentTypeService.getAllEquipmentsType();
        List<EquipmentTypeResponse> equipmentTypeResponses = equipmentTypes.stream().map(equipmentTypeMapper::toResponse).toList();
        log.debug("Récupération réussie : {} equipmentTypes trouvés", equipmentTypeResponses.size());

        return ResponseEntity.ok(equipmentTypeResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentTypeResponse> getEquipmentTypeById(@PathVariable Long id) {
        log.debug("GET {} /{} - Récupération de equipmentType", BASE_URL, id);
        return equipmentTypeService.getEquipmentTypeById(id)
                .map(equipmentType -> {
                    log.debug("EquipmentType trouvé : {} ({})", equipmentType.getLabel(), equipmentType.getId());
                    return ResponseEntity.ok(equipmentTypeMapper.toResponse(equipmentType));
                })
                .orElseGet(() -> {
                    log.warn("EquipmentType non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<EquipmentTypeResponse> createEquipmentType(@Valid @RequestBody EquipmentTypeRequest equipmentTypeRequest) {
        log.debug("POST {} - Création d'un nouveau equipmentType : {}", BASE_URL, equipmentTypeRequest.getLabel());

        EquipmentType created = equipmentTypeService.createEquipmentType(equipmentTypeMapper.toEntity(equipmentTypeRequest));

        log.info("EquipmentType créé avec succès : {} (ID = {})", created.getLabel(), created.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentTypeMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentTypeResponse> updateEquipmentType(
            @PathVariable Long id,
            @Valid @RequestBody EquipmentTypeRequest equipmentTypeRequest) {

        log.debug("PUT {}/{} - Mise à jour de equipmentType", BASE_URL, id);

        EquipmentType updated = equipmentTypeService.updateEquipmentType(equipmentTypeMapper.toEntity(equipmentTypeRequest));
        log.info("EquipmentType mis à jour avec succès : {} (ID = {})", updated.getLabel(), id);

        return ResponseEntity.ok(equipmentTypeMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentType(@PathVariable Long id) {
        log.debug("DELETE {}/{} - Suppression du equipmentType", BASE_URL, id);
        equipmentTypeService.deleteEquipmentType(id);
        log.info("EquipmentType supprimé avec succès : ID = {}", id);
        return ResponseEntity.noContent().build();
    }
}