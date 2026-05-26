package org.iftm.gerenciadorveterinarios.servicies.integrations;

import java.util.List;

import javax.transaction.Transactional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.iftm.gerenciadorveterinarios.servicies.VeterinarioService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class VeterinarioServiceITest_ArthurSantana {

    @Autowired
    private VeterinarioRepository repository;

    @Autowired
    private VeterinarioService service;

    @Test
    void deveBuscarVeterinariosPorParteDoNome() {
        // Arrange
        String parteNome = "Evaristo";

        // Act
        List<Veterinario> resultado = service.buscaVeterinariosComParteNome(parteNome);

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("Conceição Evaristo", resultado.get(0).getNome());
    }

    @Test
    void deveLancarExcecaoAoApagarIdNaoExistente() {
        Integer idInexistente = 9999;
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            service.apagarPorId(idInexistente);
        });
    }

}
