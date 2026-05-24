package org.iftm.gerenciadorveterinarios.servicies;

import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repositorio;

    private static final List<String> ESPECIES_ATENDIDAS = List.of("cachorro", "coelho", "gato", "pássaro");

    public Animal cadastrar(Animal animal) {
        if (!ESPECIES_ATENDIDAS.contains(animal.getEspecie())) {
            throw new IllegalArgumentException("Espécie " + animal.getEspecie() + " não atendida");
        }

        animal.setInternado(true);

        return repositorio.save(animal);
    }

    public Animal darAlta(Integer id) {
        Animal animal = repositorio.findById(id)
        .orElseThrow(() -> new RuntimeException(
            "Animal não encontrado com o id " + id
        ));

        animal.setInternado(false);

        return repositorio.save(animal);
    }
}
