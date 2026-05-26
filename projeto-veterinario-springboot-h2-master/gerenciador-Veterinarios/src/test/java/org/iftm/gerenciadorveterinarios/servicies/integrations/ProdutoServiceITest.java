package org.iftm.gerenciadorveterinarios.servicies.integrations;

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
@SpringBootTest
@Transactional
public class ProdutoServiceTest {

    @Autowired
    private ProdutoRepository repositorio;

    @Autowired
    private ProdutoService service;

    @Test
    @DisplayName("Deve cadastrar produto com status")
    public void deveCadastrarAnimalComStatusAtivo(){
        Produto produto = new Produto(1, "Livro de ameaças de RPG", BigDecimal.valueOf(246.99), 32, false);

        Produto salvo = service.cadastrar(produto);

        assertTrue(salvo.isAtivo());
        assertTrue(noBanco.isPresent());
        assertTrue("Livro de ameaças de RPG", noBanco.get().getDescricao());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar produto com preço negativo")
    public void deveLancarExcecaoAoCadastrarProdutoComPrecoNegativo(){
        Produto produto = new Produto(1, "Livro de ameaças de RPG", BigDecimal.valueOf(-246.99), 32, false);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produto);
        });
    }

    @Test
    @DisplayName("Deve inativar produto e persistir alteração")
    public void deveInativarProdutoEPersistirAlteracao() {
        Integer id = 1;
        
        Produto resultado = service.inativar(id);

        assertFalse(resultado.isAtivo());
    }

    @Test
    void deveLancarExcecaoAoInativarProdutoInexistente() {
        when(repositorio.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.inativar(99);
        });
    }
}
