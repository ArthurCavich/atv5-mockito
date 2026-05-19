package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar funcionário com salário abaixo do mínimo")
    public void deveLancarExcecaoAoCadastrarComSalarioAbaixoDoMinimo() {
        Funcionario invalido = new Funcionario(
                null, "João", "Auxiliar", BigDecimal.valueOf(500), false);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(invalido);
        });
        assertEquals("Salário não pode ser menor que o mínimo de R$ 1621,00", excecao.getMessage());

        verify(repositorio, never()).save(any());
    }

    @Test
    @DisplayName("Deve conceder férias ao funcionário e persistir alteração")
    public void deveConcederFeriasAoFuncionario() {
        Integer id = 1;
        Funcionario funcionario = new Funcionario(id, "Ana", "Veterinario(a)", BigDecimal.valueOf(5000), false);

        when(repositorio.findById(id)).thenReturn(Optional.of(funcionario));
        when(repositorio.save(funcionario)).thenReturn(funcionario);

        Funcionario resultado = service.concederFerias(id);

        assertTrue(resultado.isEmFerias());
        verify(repositorio).findById(id);
        verify(repositorio).save(funcionario);
    }
}
