package org.iftm.gerenciadorveterinarios.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cargo;
    private BigDecimal salario;
    private boolean emFerias;

    public Funcionario() {
    }

    public Funcionario(Integer id, String nome, String cargo, BigDecimal salario, boolean emFerias) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.emFerias = emFerias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public boolean isEmFerias() {
        return emFerias;
    }

    public void setEmFerias(boolean emFerias) {
        this.emFerias = emFerias;
    }
}
