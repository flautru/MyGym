package com.fabien.equipment_service.controller;

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
    private final EquipmentService equipementService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        log.debug("GET {} - Récupération de tous les équipements", BASE_URL);
        List<Equipment> equipmentList = equipementService.getAllEquipment();
        log.debug("Récupération réussie : {} equipements trouvés", equipmentList.size());

        return ResponseEntity.ok(equipmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        log.debug("GET {}/{} - Récupération de l'équipement", BASE_URL, id);
        return equipementService.getEquipmentById(id)
                .map(equipment -> {
                    log.debug("Equipements trouvé : {} ({})", equipment.getNom(), equipment.getId());
                    return ResponseEntity.ok(equipment);
                })
                .orElseGet(() -> {
                    log.warn("Equipement non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        log.debug("POST {} - Création d'un nouvel equipement : {}", BASE_URL, equipment.getNom());

        Equipment createdEquipment = equipementService.saveEquipment(equipment);

        log.info("Equipement créé avec succès : {} (ID = {})", createdEquipment.getNom(), createdEquipment.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        log.debug("PUT {}/{} - Mise à jour de l'équipement", BASE_URL, id);
        equipment.setId(id);

        Equipment updatedEquipment = equipementService.saveEquipment(equipment);

        log.info("Tag mis à jour avec succès : {} (ID = {})", updatedEquipment.getNom(), id);
        return ResponseEntity.ok(updatedEquipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        log.debug("DELETE {}/{} - Suppression de l'équipement", BASE_URL, id);

        equipementService.deleteEquipment(id);

        log.info("Equipement supprimé avec succès : ID = {}", id);
        return ResponseEntity.noContent().build();
    }
}