package com.fabien.equipment_service.repository;

import com.fabien.equipment_service.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Set<Tag> findAllBy();
}
