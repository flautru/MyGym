package com.fabien.equipment_service.service;

import com.fabien.equipment_service.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> getAllTag();

    Optional<Tag> getTagById(Long id);

    Tag createTag(Tag tag);

    Tag updateTag(Tag tag);

    void deleteTagById(Long id);
}
