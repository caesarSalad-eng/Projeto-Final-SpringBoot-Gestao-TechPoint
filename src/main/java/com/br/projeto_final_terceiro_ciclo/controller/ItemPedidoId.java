package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {

    private int pedido;

    private int produto;

}