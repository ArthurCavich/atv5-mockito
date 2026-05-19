package org.iftm.gerenciadorveterinarios.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private BigDecimal valor;
    private Integer tempoMinutos;
    private boolean disponivel;

    public Servico() {
    }

    public Servico(Integer id, String nome, BigDecimal valor, Integer tempoMinutos, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tempoMinutos = tempoMinutos;
        this.disponivel = disponivel;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(Integer tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
