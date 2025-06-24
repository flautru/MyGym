package com.fabien.salle_service.repository;

import com.fabien.salle_service.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
