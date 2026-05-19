package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repositorio;
}
