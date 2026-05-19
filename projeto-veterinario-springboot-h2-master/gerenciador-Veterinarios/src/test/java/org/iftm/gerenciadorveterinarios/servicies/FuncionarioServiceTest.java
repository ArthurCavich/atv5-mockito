package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.h2.command.dml.MergeUsing;
import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repositorio;

    @InjectMocks
    private FuncionarioService service;

    @Test
    @DisplayName("Retorna funcionários que não estão de férias")
    public void deveRetornarFuncionariosQueNaoEstaoDeFerias() {
        //Arrange
        Funcionario f1 = new Funcionario(1, "Ana", "Veterinario(a)", BigDecimal.valueOf(10000), false);
        Funcionario f2 = new Funcionario(2, "Bruno", "Técnico em Veterinária", BigDecimal.valueOf(4000), false);
        List<Funcionario> listaEsperada = Arrays.asList(f1, f2);

        when(repositorio.findByEmFerias(false)).thenReturn(listaEsperada);

        // Act
        List<Funcionario> resultado = service.buscarFuncionariosDisponiveis();

        // Assert
        assertEquals(2, resultado.size());
        verify(repositorio).findByEmFerias(false);
    }
}
