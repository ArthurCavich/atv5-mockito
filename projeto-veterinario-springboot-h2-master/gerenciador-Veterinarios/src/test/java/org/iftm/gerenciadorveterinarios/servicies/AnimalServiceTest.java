package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Arthur Gabriel
@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository repositorio;

    @InjectMocks
    private AnimalService service;

    @Test
    @DisplayName("Deve cadastrar animal com status internado ativo")
    public void deveCadastrarAnimalComStatusInternadoAtivo() {
        Animal animal = new Animal(1, "Bicho", "gato", 6, false);

        when(repositorio.save(any(Animal.class))).thenReturn(animal);

        Animal salvo = service.cadastrar(animal);

        assertTrue(salvo.isInternado());
        verify(repositorio).save(any(Animal.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar animal com espécie inválida")
    public void deveLancarExcecaoAoCadastrarAnimalComEspecieInvalida() {
        Animal animal = new Animal(1, "Jaca", "jacaré", 12, false);

        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(animal);
        });

        verify(repositorio, never()).save(any());
    }

    @Test
    @DisplayName("Deve dar alta ao animal e persistir alteração")
    public void deveDarAltaAoAnimalEPersistirAlteracao() {
        Integer id = 1;
        Animal animal = new Animal(id, "Bicho", "gato", 6, false);

        when(repositorio.findById(id)).thenReturn(Optional.of(animal));
        when(repositorio.save(any(Animal.class))).thenReturn(animal);

        Animal resultado = service.darAlta(id);

        assertFalse(resultado.isInternado());
        verify(repositorio).save(any(Animal.class));
    }

    @Test
    void deveLancarExcecaoAoDarAltaDeAnimalInexistente() {
        when(repositorio.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.darAlta(99);
        });

        verify(repositorio, never()).save(any());
    }
}
