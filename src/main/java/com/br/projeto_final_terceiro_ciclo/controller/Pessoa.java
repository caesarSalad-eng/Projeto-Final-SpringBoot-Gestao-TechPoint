package com.br.projeto_final_terceiro_ciclo.controller;

//Criei uma classe pessoa para praticar a Herança, onde as classes Cliente,
// PessoaFisica e PessoaJuridica herdarão a classe Pessoa

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nome;

    protected String telefone;

    @Column(unique = true)
    protected String email;

    //Atributos
    public Pessoa(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

}

//terminado