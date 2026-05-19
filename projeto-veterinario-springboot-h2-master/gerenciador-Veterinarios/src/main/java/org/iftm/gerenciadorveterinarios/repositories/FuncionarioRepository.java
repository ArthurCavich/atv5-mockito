package org.iftm.gerenciadorveterinarios.repositories;

import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findByEmFerias(boolean emFerias);
}
