package com.br.projeto_final_terceiro_ciclo.service;

import com.br.projeto_final_terceiro_ciclo.controller.PessoaFisica;
import com.br.projeto_final_terceiro_ciclo.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    private final PessoaFisicaRepository repository;

    @Autowired
    public PessoaFisicaService(PessoaFisicaRepository repository) {

        this.repository = repository;

    }

    public PessoaFisica cadastrar(PessoaFisica pessoaFisica) {

        return repository.save(pessoaFisica);

    }

    public Optional<PessoaFisica> consultar(int id) {

        return repository.findById(id);

    }

    public PessoaFisica atualizar(PessoaFisica pessoaAtualizada) {

        Optional<PessoaFisica> pessoaOptional = repository.findById(pessoaAtualizada.getId());

        if (pessoaOptional.isPresent()) {

            PessoaFisica existente = pessoaOptional.get();

            existente.setNome(pessoaAtualizada.getNome());
            existente.setTelefone(pessoaAtualizada.getTelefone());
            existente.setEmail(pessoaAtualizada.getEmail());
            existente.setEndereco(pessoaAtualizada.getEndereco());

            existente.setCpf(pessoaAtualizada.getCpf());

            return repository.save(existente);

        }

        return null;
    }

    public boolean deletar(int id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

            return true;
        }

        return false;
    }


    public List<PessoaFisica> listar() {

        return repository.findAll();

    }
}