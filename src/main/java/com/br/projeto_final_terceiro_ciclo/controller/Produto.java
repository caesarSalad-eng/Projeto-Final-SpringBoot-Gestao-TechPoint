package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String nome;

    private String categoria;

    private double preco;

    private int estoque;

    public Produto(int codigo, String nome, String categoria, double preco, int estoque) {

        this.codigo = codigo;

        this.nome = nome;

        this.categoria = categoria;

        this.preco = preco;

        this.estoque = estoque;

    }

}