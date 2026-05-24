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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'darAlta'");
    }
}
