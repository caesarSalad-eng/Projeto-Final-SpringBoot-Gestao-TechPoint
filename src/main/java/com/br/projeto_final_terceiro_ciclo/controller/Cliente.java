package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente extends Pessoa {

    private String endereco;

    public Cliente(int id, String nome, String telefone, String email, String endereco) {
        super(id, nome, telefone, email);
        this.endereco = endereco;
    }
}

//terminado