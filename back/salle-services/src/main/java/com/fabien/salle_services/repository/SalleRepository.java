package com.fabien.salle_services.repository;

import com.fabien.salle_services.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle,Long> {
}
