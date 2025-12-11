package com.br.projeto_final_terceiro_ciclo.controller;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(int id, Cliente cliente) {

        this.id = id;

        this.cliente = cliente;

    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
    }

    public double calcularTotal() {

        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();

    }

}