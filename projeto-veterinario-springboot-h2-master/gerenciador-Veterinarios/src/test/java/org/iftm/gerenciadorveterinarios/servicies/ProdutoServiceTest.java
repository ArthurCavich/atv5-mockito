package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repositorio;

    @InjectMocks
    private ProdutoService service;

    @Test
    @DisplayName("Deve cadastrar produto com status")
    public void deveCadastrarAnimalComStatusAtivo(){
        Produto produto = new Produto(1, "Livro de ameaças de RPG", BigDecimal.valueOf(246), 32, false);

        when(repositorio.save(any(Produto.class))).thenReturn(produto);

        Produto salvo = service.cadastrar(produto);

        assertTrue(salvo.isAtivo());
        verify(repositorio).save(any(Produto.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar produto com preço negativo")
    public void deveLancarExcecaoAoCadastrarProdutoComPrecoNegativo(){
        Produto produto = new Produto(1, "Livro de ameaças de RPG", BigDecimal.valueOf(-246), 32, false);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produto);
        });

        verify(repositorio, never()).save(any());
    }
}
