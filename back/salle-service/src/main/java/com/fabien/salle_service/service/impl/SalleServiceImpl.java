package com.fabien.salle_service.service.impl;

import com.fabien.salle_service.model.Salle;
import com.fabien.salle_service.repository.SalleRepository;
import com.fabien.salle_service.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
