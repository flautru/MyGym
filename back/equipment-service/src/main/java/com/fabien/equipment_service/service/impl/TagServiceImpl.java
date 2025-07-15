package com.fabien.equipment_service.service.impl;

import com.fabien.equipment_service.model.Tag;
import com.fabien.equipment_service.repository.TagRepository;
import com.fabien.equipment_service.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Set<Tag> getAllTag() {
        return tagRepository.findAllBy();
    }

    @Override
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    @Transactional
    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag updateTag(Tag tag) {
        if (tag.getId() == null) {
            throw new IllegalArgumentException("Tag id cant be null");
        }

        if (!tagRepository.existsById(tag.getId())) {
            throw new EntityNotFoundException("Tag not found with id : " + tag.getId());
        }

        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void deleteTagById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Tag id cant be null");
        }

        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException("Tag not found with id : " + id);
        }

        tagRepository.deleteById(id);
    }
}
