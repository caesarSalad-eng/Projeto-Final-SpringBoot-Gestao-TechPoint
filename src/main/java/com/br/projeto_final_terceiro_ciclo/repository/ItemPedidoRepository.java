package com.br.projeto_final_terceiro_ciclo.repository;

import com.br.projeto_final_terceiro_ciclo.controller.ItemPedido;
import com.br.projeto_final_terceiro_ciclo.controller.ItemPedidoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoId> {


}