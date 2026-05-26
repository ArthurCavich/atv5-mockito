package org.iftm.gerenciadorveterinarios.services.integrations;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.iftm.gerenciadorveterinarios.servicies.VeterinarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@Transactional
public class VeterinarioServiceTest_ArthurMorais {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private VeterinarioService veterinarioService;

    @Test
    @DisplayName("Deve retornar uma lista com apenas dois veterinários")
    public void testeBuscaVeterinariosComParteNome() {
        Integer idExistente = 1;
        String nomeBuscado = "Conceição";
        String nomeEsperado = "Conceição Evaristo";

        List<Veterinario> resultado = veterinarioService.buscaVeterinariosComParteNome(nomeBuscado);

        assertEquals(1, resultado.size());
        assertEquals(nomeEsperado, resultado.get(0).getNome);
    }

    
    @Test
    @DisplayName("Deve lançar exceção caso não encontre o id")
    public void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        Integer idInexistente = 9999;

        assertThrows(RuntimeException.class, () -> {
            veterinarioService.apagarPorId(idInexistente);
        });
    }

}