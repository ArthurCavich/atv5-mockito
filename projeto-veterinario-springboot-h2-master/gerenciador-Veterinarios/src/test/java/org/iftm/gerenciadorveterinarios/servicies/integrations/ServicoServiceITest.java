package org.iftm.gerenciadorveterinarios.servicies.integrations;

import javax.transaction.Transactional;

import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.iftm.gerenciadorveterinarios.servicies.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ServicoServiceITest {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private ServicoService service;

    

}
