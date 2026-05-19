package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repositorio;

    @InjectMocks
    private FuncionarioService service;
}
