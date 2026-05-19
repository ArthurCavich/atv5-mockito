package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
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
}
