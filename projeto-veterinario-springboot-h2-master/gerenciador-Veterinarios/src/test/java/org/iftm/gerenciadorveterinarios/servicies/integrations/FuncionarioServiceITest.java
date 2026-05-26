package org.iftm.gerenciadorveterinarios.servicies.integrations;

import java.math.BigDecimal;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.iftm.gerenciadorveterinarios.servicies.FuncionarioService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FuncionarioServiceITest {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private FuncionarioRepository repository;

    @Test
    @DisplayName("Deve cadastrar funcionário sem estar de férias")
    void deveCadastrarFuncionarioSemEstarDeFerias() {
        // Arrange
        Funcionario funcionario = new Funcionario(
                null, "Maria", "Auxiliar", BigDecimal.valueOf(3000), true);

        // Act
        Funcionario salvo = service.cadastrar(funcionario);

        // Assert
        assertFalse(salvo.isEmFerias());
        assertNotNull(salvo.getId());
        Funcionario noBanco = repository.findById(salvo.getId()).orElseThrow();
        assertFalse(noBanco.isEmFerias());
        assertEquals("Maria", noBanco.getNome());
    }

    @Test
    @DisplayName("Deve retornar funcionários que não estão de férias")
    void deveRetornarFuncionariosQueNaoEstaoDeFerias() {
        // Arrange
        // import.sql: Ana (id 1) e Bruno (id 2) com em_ferias = false

        // Act
        List<Funcionario> resultado = service.buscarFuncionariosDisponiveis();

        // Assert
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(f -> "Ana".equals(f.getNome())));
        assertTrue(resultado.stream().anyMatch(f -> "Bruno".equals(f.getNome())));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar funcionário com salário abaixo do mínimo")
    void deveLancarExcecaoAoCadastrarComSalarioAbaixoDoMinimo() {
        // Arrange
        long quantidadeAntes = repository.count();
        Funcionario invalido = new Funcionario(
                null, "João", "Auxiliar", BigDecimal.valueOf(500), false);

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(invalido);
        });

        // Assert
        assertEquals("Salário não pode ser menor que o mínimo de R$ 1621,00", ex.getMessage());
        assertEquals(quantidadeAntes, repository.count());
    }

    @Test
    @DisplayName("Deve conceder férias ao funcionário e persistir no banco")
    void deveConcederFeriasAoFuncionario() {
        // Arrange
        Integer id = 1; // Ana no import.sql

        // Act
        Funcionario resultado = service.concederFerias(id);

        // Assert
        assertTrue(resultado.isEmFerias());
        Funcionario noBanco = repository.findById(id).orElseThrow();
        assertTrue(noBanco.isEmFerias());
    }

    @Test
    @DisplayName("Deve lançar exceção ao apagar funcionário inexistente")
    void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange
        int quantidadeOriginal = 3; // 3 funcionários no import.sql
        Integer idInexistente = 999;

        // Act
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.apagarPorId(idInexistente);
        });

        // Assert
        assertEquals("Funcionário não encontrado com id: 999", ex.getMessage());
        assertEquals(quantidadeOriginal, repository.count());
    }
}
