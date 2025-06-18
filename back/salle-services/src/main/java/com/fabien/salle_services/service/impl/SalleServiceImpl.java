package com.fabien.salle_services.service.impl;

import com.fabien.salle_services.model.Salle;
import com.fabien.salle_services.repository.SalleRepository;
import com.fabien.salle_services.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SalleServiceImpl implements SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Optional<Salle> getSalleById(Long id) {
        return salleRepository.findById(id);
    }

    public Salle saveSalle(Salle salles) {
        return salleRepository.save(salles);
    }

    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }

}
