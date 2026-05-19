package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repositorio;
}
