package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository repositorio;

    @InjectMocks
    private AnimalService service;

    @Test
    @DisplayName("Deve cadastrar animal com status internado ativo")
    public void deveCadastrarAnimalComStatusInternadoAtivo(){
        Animal animal = new Animal(1, "Bicho", "gato", 6, false);

        when(repositorio.save(any(Animal.class))).thenReturn(animal);

        Animal salvo = service.cadastrar(animal);

        assertTrue(salvo.isInternado());
        verify(repositorio).save(any(Animal.class));
    }
}
