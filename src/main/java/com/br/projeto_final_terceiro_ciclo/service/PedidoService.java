package com.br.projeto_final_terceiro_ciclo.service;

import com.br.projeto_final_terceiro_ciclo.controller.Pedido;
import com.br.projeto_final_terceiro_ciclo.controller.ItemPedido;
import com.br.projeto_final_terceiro_ciclo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    public Pedido cadastrar(Pedido pedido) {

        for (ItemPedido item : pedido.getItens()) {

            produtoService.atualizarEstoque(item.getProduto().getCodigo(), item.getQuantidade());

            item.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);

    }

    public Optional<Pedido> consultar(int id) {

        return pedidoRepository.findById(id);

    }

    public Pedido atualizar(Pedido pedidoAtualizado) {

        if (pedidoRepository.existsById(pedidoAtualizado.getId())) {

            return pedidoRepository.save(pedidoAtualizado);
        }
        return null; // Não encontrado
    }

    public boolean deletar(int id) {

        if (pedidoRepository.existsById(id)) {

            pedidoRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public List<Pedido> listar() {

        return pedidoRepository.findAll();

    }
}