package com.fabien.salle_services.service;

import com.fabien.salle_services.model.Salle;

import java.util.List;
import java.util.Optional;

public interface SalleService {
    List<Salle> getAllSalles();
    Optional<Salle> getSalleById(Long id);
    Salle saveSalle(Salle salles);
    void deleteSalle(Long id);
}
