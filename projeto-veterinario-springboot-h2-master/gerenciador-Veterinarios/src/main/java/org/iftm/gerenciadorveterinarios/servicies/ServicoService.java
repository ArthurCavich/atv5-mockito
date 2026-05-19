package org.iftm.gerenciadorveterinarios.servicies;

import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repositorio;

    @Transactional(readOnly = true)
    public List<Servico> buscarServicosDisponiveis() {
        return repositorio.findByDisponivel(true);
    }
}

