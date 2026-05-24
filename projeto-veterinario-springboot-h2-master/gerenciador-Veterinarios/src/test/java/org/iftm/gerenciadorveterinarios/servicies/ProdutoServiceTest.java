package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Arthur Gabriel
@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repositorio;

    @InjectMocks
    private ProdutoService service;

    @Test
    @DisplayName("Deve cadastrar produto com status")
    public void deveCadastrarAnimalComStatusAtivo(){
        Produto produto = new Produto(1, "Livro de ameaças de RPG", BigDecimal.valueOf(246.99), 32, false);

        when(repositorio.save(any(Produto.class))).thenReturn(produto);

        Produto salvo = service.cadastrar(produto);

        assertTrue(salvo.isAtivo());
        verify(repositorio).save(any(Produto.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar produto com preço negativo")
    public void deveLancarExcecaoAoCadastrarProdutoComPrecoNegativo(){
        Produto produto = new Produto(1, "Livro de ameaças de RPG", BigDecimal.valueOf(-246.99), 32, false);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produto);
        });

        verify(repositorio, never()).save(any());
    }

    @Test
    @DisplayName("Deve inativar produto e persistir alteração")
    public void deveInativarProdutoEPersistirAlteracao() {
        Integer id = 1;
        Produto produto = new Produto(id, "Livro de ameaças de RPG", BigDecimal.valueOf(246.99), 32, false);

        when(repositorio.findById(id)).thenReturn(Optional.of(produto));
        when(repositorio.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = service.inativar(id);

        assertFalse(resultado.isAtivo());
        verify(repositorio).save(any(Produto.class));
    }

    @Test
    void deveLancarExcecaoAoInativarProdutoInexistente() {
        when(repositorio.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.inativar(99);
        });

        verify(repositorio, never()).save(any());
    }
}
