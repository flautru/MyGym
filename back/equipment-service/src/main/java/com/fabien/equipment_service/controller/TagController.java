package com.fabien.equipment_service.controller;

import com.fabien.equipment_service.dto.TagRequest;
import com.fabien.equipment_service.dto.TagResponse;
import com.fabien.equipment_service.mapper.TagMapper;
import com.fabien.equipment_service.model.Tag;
import com.fabien.equipment_service.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(TagController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class TagController {

    protected static final String BASE_URL = "/api/tags";

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<Set<TagResponse>> getAllTags() {
        log.debug("GET {} /tags - Récupération de tous les tags", BASE_URL);

        Set<Tag> tags = tagService.getAllTag();

        log.debug("Récupération réussie : {} tags trouvés", tags.size());
        return ResponseEntity.ok(tagMapper.toResponseSet(tags));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Long id) {
        log.debug("GET {}/{} - Récupération du tag", BASE_URL, id);

        return tagService.getTagById(id)
                .map(tag -> {
                    log.debug("Tag trouvé : {} ({})", tag.getLabel(), tag.getId());
                    return ResponseEntity.ok(tagMapper.toResponse(tag));
                })
                .orElseGet(() -> {
                    log.warn("Tag non trouvé pour l'ID : {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<TagResponse> createTag(@Valid @RequestBody TagRequest tagRequest) {
        log.debug("POST {} - Création d'un nouveau tag : {}", BASE_URL, tagRequest.getLabel());

        Tag createdTag = tagService.createTag(tagMapper.toEntity(tagRequest));

        log.info("Tag créé avec succès : {} (ID = {})", createdTag.getLabel(), createdTag.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(tagMapper.toResponse(createdTag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponse> updateTag(
            @PathVariable Long id,
            @Valid @RequestBody TagRequest tagRequest) {

        log.debug("PUT {}/{} - Mise à jour du tag", BASE_URL, id);

        Tag updatedTag = tagService.updateTag(tagMapper.toEntity(tagRequest));

        log.info("Tag mis à jour avec succès : {} (ID = {})", updatedTag.getLabel(), id);
        return ResponseEntity.ok(tagMapper.toResponse(updatedTag));
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