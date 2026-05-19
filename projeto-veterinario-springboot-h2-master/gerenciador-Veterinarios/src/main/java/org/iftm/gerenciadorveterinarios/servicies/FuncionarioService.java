package org.iftm.gerenciadorveterinarios.servicies;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repositorio;

    @Transactional(readOnly = true)
    public List<Funcionario> buscarFuncionariosDisponiveis() {
        return repositorio.findByEmFerias(false);
    }
}
