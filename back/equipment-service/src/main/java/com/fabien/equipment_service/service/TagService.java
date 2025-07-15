package com.fabien.equipment_service.service;

import com.fabien.equipment_service.model.Tag;

import java.util.Optional;
import java.util.Set;

public interface TagService {

    Set<Tag> getAllTag();

    Optional<Tag> getTagById(Long id);

    Tag createTag(Tag tag);

    Tag updateTag(Tag tag);

    void deleteTagById(Long id);
}
