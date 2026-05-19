package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @Mock
    private ServicoRepository repositorio;

    @InjectMocks
    private ServicoService service;
}
