package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoas_juridicas")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PessoaJuridica extends Cliente {

    private String cnpj;

    private String razaoSocial;

    public PessoaJuridica(int id, String nome, String telefone, String email, String endereco, String cnpj) {
        super(id, nome, telefone, email, endereco);
        this.cnpj = cnpj;
        this.razaoSocial = null;
    }

    public PessoaJuridica(int id, String nome, String telefone, String email, String endereco, String cnpj, String razaoSocial) {
        super(id, nome, telefone, email, endereco);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

}