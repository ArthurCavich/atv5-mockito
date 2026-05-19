package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1621.00");

    @Autowired
    private FuncionarioRepository repositorio;

    @Transactional(readOnly = true)
    public List<Funcionario> buscarFuncionariosDisponiveis() {
        return repositorio.findByEmFerias(false);
    }

    @Transactional
    public Funcionario cadastrar(Funcionario funcionario) {
        if (funcionario.getSalario() == null
                || funcionario.getSalario().compareTo(SALARIO_MINIMO) < 0) {
            throw new IllegalArgumentException(
                    "Salário não pode ser menor que o mínimo de R$ 1621,00");
        }
        return repositorio.save(funcionario);
    }
}
