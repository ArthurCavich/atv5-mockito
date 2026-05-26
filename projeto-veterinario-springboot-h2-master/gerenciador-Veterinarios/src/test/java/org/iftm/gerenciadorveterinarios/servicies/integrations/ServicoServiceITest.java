package org.iftm.gerenciadorveterinarios.servicies.integrations;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.iftm.gerenciadorveterinarios.servicies.ServicoService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ServicoServiceITest {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private ServicoService service;

    @Test
    @DisplayName("Deve cadastrar serviço com status disponível ativo")
    public void deveCadastrarServicoComStatusDisponivelAtivo() {
        // Arrange
        Servico servico = new Servico(null, "Exame", BigDecimal.valueOf(200), 45, false);
        // Act
        Servico salvo = service.cadastrar(servico);
        // Assert
        assertTrue(salvo.isDisponivel());
        assertNotNull(salvo.getId());
        Servico noBanco = repository.findById(salvo.getId()).orElseThrow();
        assertTrue(noBanco.isDisponivel());
        assertEquals("Exame", noBanco.getNome());
    }

    @Test
    @DisplayName("Deve retornar serviços disponíveis do banco")
    void deveRetornarServicosDisponiveis() {
        // Arrange
        // import.sql: Consulta (id 1) e Vacina (id 2) com disponivel = true
        // Act
        List<Servico> resultado = service.buscarServicosDisponiveis();
        // Assert
        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(s -> "Consulta".equals(s.getNome())));
        assertTrue(resultado.stream().anyMatch(s -> "Vacina".equals(s.getNome())));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar serviço com valor negativo")
    void deveLancarExcecaoAoCadastrarComValorNegativo() {
        // Arrange
        long quantidadeAntes = repository.count();
        Servico invalido = new Servico(null, "Consulta inválida", BigDecimal.valueOf(-10), 30, true);
        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(invalido);
        });
        // Assert
        assertEquals("Valor do serviço não pode ser negativo", ex.getMessage());
        assertEquals(quantidadeAntes, repository.count());
    }

    @Test
    @DisplayName("Deve indisponibilizar serviço e persistir no banco")
    void deveIndisponibilizarServico() {
        // Arrange
        Integer id = 1; // Consulta no import.sql
        // Act
        Servico resultado = service.indisponibilizar(id);
        // Assert
        assertFalse(resultado.isDisponivel());
        Servico noBanco = repository.findById(id).orElseThrow();
        assertFalse(noBanco.isDisponivel());
    }

    @Test
    @DisplayName("Deve lançar exceção ao apagar serviço inexistente")
    void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange
        int quantidadeOriginal = 3; // 3 serviços no import.sql
        Integer idInexistente = 999;
        // Act
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.apagarPorId(idInexistente);
        });
        // Assert
        assertEquals("Serviço não encontrado com id: 999", ex.getMessage());
        assertEquals(quantidadeOriginal, repository.count());
    }

}
