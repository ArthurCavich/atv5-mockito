package org.iftm.gerenciadorveterinarios.servicies.integrations;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.iftm.gerenciadorveterinarios.servicies.VeterinarioService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void deveBuscarVeterinarioPorIdComSucessoComLimiteCaracteres() {
        Integer idExistente = 1;
        Optional<Veterinario> resultado = service.buscaVeterinariosPeloId(idExistente);
        assertTrue(resultado.isPresent());
        assertEquals("Conceição ", resultado.get().getNome()); // 10 primeiros caracteres
    }

    @Test
    void deveSalvarVeterinarioNoBancoDeDados() {
        Veterinario novoVet = new Veterinario(
                null, "Dra. Marcia", "marcia@gmail.com", "Grandes Animais", BigDecimal.valueOf(5500.0));

        Veterinario salvo = service.salvar(novoVet);

        assertNotNull(salvo.getId());
        assertEquals("Dra. Marcia", salvo.getNome());

        Optional<Veterinario> vetNoBanco = repository.findById(salvo.getId());
        assertTrue(vetNoBanco.isPresent());
        assertEquals("marcia@gmail.com", vetNoBanco.get().getEmail());
    }

    @Test
    void deveLancarExcecaoAoApagarIdNaoExistente() {
        Integer idInexistente = 9999;
        int quantidadeOriginal = 2; // os 2 do import.sql
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            service.apagarPorId(idInexistente);
        });
        assertEquals(quantidadeOriginal, service.buscaTodosVeterinarios().size());
    }

}
