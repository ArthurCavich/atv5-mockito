package org.iftm.gerenciadorveterinarios.servicies.integrations;

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
@SpringBootTest
@Transactional
public class AnimalServiceTest {

    @Autowired
    private AnimalRepository repositorio;

    @Autowired
    private AnimalService service;

    @Test
    @DisplayName("Deve cadastrar animal com status internado ativo")
    public void deveCadastrarAnimalComStatusInternadoAtivo() {
        Animal animal = new Animal(1, "Bicho", "gato", 6, false);

        Animal salvo = service.cadastrar(animal);

        assertNotNull(salvo.getId(), "O banco H2 deveria ter gerado um ID automático!");
        assertTrue(salvo.isInternado());

        Animal noBanco = repositorio.findById(salvo.getId());
        assertTrue(noBanco.isPresent());
        assertTrue("Bicho", noBanco.get().getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar animal com espécie inválida")
    public void deveLancarExcecaoAoCadastrarAnimalComEspecieInvalida() {
        Animal animal = new Animal(1, "Jaca", "jacaré", 12, false);

        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(animal);
        });
    }

    @Test
    @DisplayName("Deve dar alta ao animal e persistir alteração")
    public void deveDarAltaAoAnimalEPersistirAlteracao() {
        Integer id = 1;

        Animal resultado = service.darAlta(id);

        assertFalse(resultado.isInternado());
    }

    @Test
    void deveLancarExcecaoAoDarAltaDeAnimalInexistente() {
        assertThrows(RuntimeException.class, () -> {
            service.darAlta(99);
        });
    }
}
