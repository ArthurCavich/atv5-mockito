package org.iftm.gerenciadorveterinarios.repositories;

import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByDisponivel(boolean disponivel);
}
