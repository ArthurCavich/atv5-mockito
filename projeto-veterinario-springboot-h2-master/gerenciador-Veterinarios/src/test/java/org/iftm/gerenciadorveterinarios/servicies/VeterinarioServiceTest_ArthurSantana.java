package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

@ExtendWith(MockitoExtension.class)
public class VeterinarioServiceTest_ArthurSantana {

    @Mock
    private VeterinarioRepository repositorio;

    @InjectMocks
    private VeterinarioService service;

    @Test
    @DisplayName("Deve retornar uma lista com dois veterinários")
    public void deveRetornarDoisVeterinariosAoBuscarPorParteDoNome() {
        // Arrange
        Veterinario vet1 = new Veterinario(1, "João Silva", "joao@email.com", "Clínica", BigDecimal.valueOf(5000));
        Veterinario vet2 = new Veterinario(2, "Maria Silva", "maria@email.com", "Cirurgia", BigDecimal.valueOf(6000));
        List<Veterinario> listaEsperada = Arrays.asList(vet1, vet2);

        when(repositorio.findByNomeContains("Silva")).thenReturn(listaEsperada);

        // Act
        List<Veterinario> resultado = service.buscaVeterinariosComParteNome("Silva");

        // Assert
        assertEquals(2, resultado.size());
        verify(repositorio).findByNomeContains("Silva");
    }

    @Test
    @DisplayName("Deve lançar tratamento de exceção caso não encontre o ID e verifica que o mockito bloqueou o acesso indevido")
    public void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange
        Integer idInexistente = 999;
        when(repositorio.findById(idInexistente)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> {
            service.apagarPorId(idInexistente);  // nome do método que você vai criar
        });

        // Verificar que delete NUNCA foi chamado
        verify(repositorio, never()).delete(any());
    }

}
