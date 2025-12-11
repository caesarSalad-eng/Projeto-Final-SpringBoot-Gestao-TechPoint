package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens_pedido")
@Data // (Lombok) Gera Getters, Setters, toString, etc.
@NoArgsConstructor // (Lombok) Requisito JPA
public class ItemPedido {

    // 1. Chave Composta: Usa a classe auxiliar ItemPedidoId
    @EmbeddedId
    private ItemPedidoId id;

    // 2. Relacionamento com Produto (ManyToOne)
    @MapsId("produto") // Mapeia o campo 'produto' da chave composta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_codigo") // Coluna que referencia o Produto
    private Produto produto;

    // 3. Relacionamento com Pedido (ManyToOne)
    @MapsId("pedido") // Mapeia o campo 'pedido' da chave composta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id") // Coluna que referencia o Pedido
    private Pedido pedido;

    private int quantidade;

    // Construtor manual (usaremos este na camada Service)
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.id = new ItemPedidoId(0, produto.getCodigo());
        // Nota: O 'pedidoId' será setado quando o item for adicionado ao Pedido
    }

    // Mantido como getter para ser serializado
    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

}