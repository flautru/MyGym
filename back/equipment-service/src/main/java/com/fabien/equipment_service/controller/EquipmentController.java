package com.fabien.equipment_service.controller;

import com.fabien.equipment_service.dto.EquipmentRequest;
import com.fabien.equipment_service.dto.EquipmentResponse;
import com.fabien.equipment_service.mapper.EquipmentMapper;
import com.fabien.equipment_service.model.Equipment;
import com.fabien.equipment_service.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EquipmentController.BASE_URL)
@Slf4j
public class EquipmentController {

    protected static final String BASE_URL = "/api/equipments";
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;

    @GetMapping
    public ResponseEntity<List<EquipmentResponse>> getAllEquipments() {
        log.debug("GET {} - Récupération de tous les équipements", BASE_URL);
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        List<EquipmentResponse> responseList = equipmentList.stream().map(equipmentMapper::toResponse).toList();
        log.debug("Récupération réussie : {} equipements trouvés", responseList.size());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponse> getEquipmentById(@PathVariable Long id) {
        log.debug("GET {}/{} - Récupération de l'équipement", BASE_URL, id);
        return equipmentService.getEquipmentById(id)
                .map(equipment -> {
                    log.debug("Equipements trouvé : {} ({})", equipment.getNom(), equipment.getId());
                    return ResponseEntity.ok(equipmentMapper.toResponse(equipment));
                })
                .orElseGet(() -> {
                    log.warn("Equipement non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<EquipmentResponse> createEquipment(@RequestBody EquipmentRequest dto) {
        log.debug("POST {} - Création d'un nouvel equipement : {}", BASE_URL, dto.getNom());
        Equipment equipment = equipmentMapper.toEntity(dto);
        Equipment createdEquipment = equipmentService.saveEquipment(equipment);

        log.info("Equipement créé avec succès : {} (ID = {})", createdEquipment.getNom(), createdEquipment.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentMapper.toResponse(createdEquipment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponse> updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequest equipmentRequest) {
        log.debug("PUT {}/{} - Mise à jour de l'équipement", BASE_URL, id);
        Equipment equipment = equipmentMapper.toEntity(equipmentRequest);
        equipment.setId(id);

        Equipment updatedEquipment = equipmentService.saveEquipment(equipment);
        EquipmentResponse equipmentResponse = equipmentMapper.toResponse(updatedEquipment);
        log.info("Tag mis à jour avec succès : {} (ID = {})", equipmentResponse.getNom(), id);
        return ResponseEntity.ok(equipmentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        log.debug("DELETE {}/{} - Suppression de l'équipement", BASE_URL, id);

        equipmentService.deleteEquipment(id);

        log.info("Equipement supprimé avec succès : ID = {}", id);
        return ResponseEntity.noContent().build();
    }
}