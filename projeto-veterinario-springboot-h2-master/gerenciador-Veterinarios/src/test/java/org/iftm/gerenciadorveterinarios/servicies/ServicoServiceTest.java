package org.iftm.gerenciadorveterinarios.servicies;

import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @Mock
    private ServicoRepository repositorio;

    @InjectMocks
    private ServicoService service;

    @Test
    @DisplayName("Deve retornar serviços disponíveis")
    public void deveRetornarServicosDisponiveis() {
        // Arrange
        Servico s1 = new Servico(1, "Consulta", BigDecimal.valueOf(150), 30, true);
        Servico s2 = new Servico(2, "Vacina", BigDecimal.valueOf(80), 15, true);
        List<Servico> listaEsperada = Arrays.asList(s1, s2);

        when(repositorio.findByDisponivel(true)).thenReturn(listaEsperada);

        // Act
        List<Servico> resultado = service.buscarServicosDisponiveis();

        // Assert
        assertEquals(2, resultado.size());
        verify(repositorio).findByDisponivel(true);
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar serviço com valor negativo")
    public void deveLancarExcecaoAoCadastrarComValorNegativo() {
        Servico invalido = new Servico(
                null, "Consulta inválida", BigDecimal.valueOf(-10), 30, true);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(invalido);
        });
        assertEquals("Valor do serviço não pode ser negativo", excecao.getMessage());

        verify(repositorio, never()).save(any());
    }
}
