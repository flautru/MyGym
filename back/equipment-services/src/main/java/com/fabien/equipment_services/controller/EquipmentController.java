package com.fabien.equipment_services.controller;

import com.fabien.equipment_services.model.Equipment;
import com.fabien.equipment_services.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipements")
public class EquipmentController {

    @Autowired
    private EquipmentService equipementService;


    @GetMapping
    public List<Equipment> getAllEquipements() {
        return equipementService.getAllEquipment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipementById(@PathVariable Long id) {
        Optional<Equipment> equipement = equipementService.getEquipmentById(id);
        return equipement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipment createEquipement(@RequestBody Equipment equipment) {
        return equipementService.saveEquipment(equipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipement(@PathVariable Long id, @RequestBody Equipment equipment) {
        Optional<Equipment> existingEquipement = equipementService.getEquipmentById(id);
        if (existingEquipement.isPresent()) {
            equipment.setId(id);
            return ResponseEntity.ok(equipementService.saveEquipment(equipment));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}