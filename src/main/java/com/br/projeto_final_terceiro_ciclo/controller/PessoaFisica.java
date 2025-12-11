package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoas_fisicas")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(int id, String nome, String telefone, String email, String endereco, String cpf) {

        super(id, nome, telefone, email, endereco);

        this.cpf = cpf;

    }

}