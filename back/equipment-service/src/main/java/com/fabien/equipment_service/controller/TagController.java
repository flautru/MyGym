package com.fabien.equipment_service.controller;

import com.fabien.equipment_service.model.Tag;
import com.fabien.equipment_service.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TagController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class TagController {

    protected static final String BASE_URL = "/api/tags";

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        log.debug("GET {} /tags - Récupération de tous les tags", BASE_URL);

        List<Tag> tags = tagService.getAllTag();

        log.debug("Récupération réussie : {} tags trouvés", tags.size());
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        log.debug("GET {}/{} - Récupération du tag", BASE_URL, id);

        return tagService.getTagById(id)
                .map(tag -> {
                    log.debug("Tag trouvé : {} ({})", tag.getLabel(), tag.getId());
                    return ResponseEntity.ok(tag);
                })
                .orElseGet(() -> {
                    log.warn("Tag non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@Valid @RequestBody Tag tag) {
        log.debug("POST {} - Création d'un nouveau tag : {}", BASE_URL, tag.getLabel());

        Tag createdTag = tagService.createTag(tag);

        log.info("Tag créé avec succès : {} (ID = {})", createdTag.getLabel(), createdTag.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(
            @PathVariable Long id,
            @Valid @RequestBody Tag tag) {

        log.debug("PUT {}/{} - Mise à jour du tag", BASE_URL, id);
        tag.setId(id);

        Tag updatedTag = tagService.updateTag(tag);

        log.info("Tag mis à jour avec succès : {} (ID = {})", updatedTag.getLabel(), id);
        return ResponseEntity.ok(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        log.debug("DELETE {}/{} - Suppression du tag", BASE_URL, id);

        tagService.deleteTagById(id);

        log.info("Tag supprimé avec succès : ID = {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/equipments")
    public ResponseEntity<?> getEquipmentsByTag(@PathVariable Long id) {
        log.debug("GET {}/{}/equipments - Récupération des équipements du tag", BASE_URL, id);

        return tagService.getTagById(id)
                .map(tag -> {
                    log.debug("Tag trouvé : {} avec {} équipements",
                            tag.getLabel(), tag.getEquipments().size());
                    return ResponseEntity.ok(tag.getEquipments());
                })
                .orElseGet(() -> {
                    log.warn("Tag non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }
}