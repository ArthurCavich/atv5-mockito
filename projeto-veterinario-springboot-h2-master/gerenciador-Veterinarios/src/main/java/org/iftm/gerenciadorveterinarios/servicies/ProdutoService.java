package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repositorio;

    public Produto cadastrar(Produto produto) {
        if ((produto.getPreco().compareTo(BigDecimal.valueOf(0))) < 0) {
            throw new IllegalArgumentException("O preço " + produto.getPreco() + " é inválido para o produto");
        }

        produto.setAtivo(true);

        return repositorio.save(produto);
    }

    public Produto inativar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inativar'");
    }
}
