package com.br.projeto_final_terceiro_ciclo.service;

import com.br.projeto_final_terceiro_ciclo.controller.Produto;
import com.br.projeto_final_terceiro_ciclo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para operações de BD

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {

        this.repository = repository;

    }

    public Produto cadastrar(Produto produto) {

        return repository.save(produto);

    }

    public Optional<Produto> consultar(int codigo) {

        return repository.findById(codigo);

    }

    public List<Produto> listar() {

        return repository.findAll();

    }

    @Transactional
    public boolean atualizarEstoque(int codigo, int quantidadeVendida) {

        Optional<Produto> produtoOptional = repository.findById(codigo);

        if (produtoOptional.isPresent()) {

            Produto produto = produtoOptional.get();

            if (quantidadeVendida <= produto.getEstoque()) {

                produto.setEstoque(produto.getEstoque() - quantidadeVendida);

                repository.save(produto);

                return true;

            } else {

                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());

            }
        }

        return false;

    }

    public Produto atualizar(Produto produtoAtualizado) {

        if (repository.existsById(produtoAtualizado.getCodigo())) {

            return repository.save(produtoAtualizado);

        }

        return null;

    }

    public boolean deletar(int codigo) {

        if (repository.existsById(codigo)) {

            repository.deleteById(codigo);

            return true;
        }

        return false;

    }
}