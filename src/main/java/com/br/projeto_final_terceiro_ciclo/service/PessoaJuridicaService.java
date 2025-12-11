package com.br.projeto_final_terceiro_ciclo.service;

import com.br.projeto_final_terceiro_ciclo.controller.PessoaJuridica;
import com.br.projeto_final_terceiro_ciclo.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository repository;

    @Autowired
    public PessoaJuridicaService(PessoaJuridicaRepository repository) {

        this.repository = repository;

    }

    public PessoaJuridica cadastrar(PessoaJuridica pessoaJuridica) {

        return repository.save(pessoaJuridica);

    }

    public Optional<PessoaJuridica> consultar(int id) {
        return repository.findById(id);
    }

    public PessoaJuridica atualizar(PessoaJuridica pessoaAtualizada) {

        Optional<PessoaJuridica> pessoaOptional = repository.findById(pessoaAtualizada.getId());

        if (pessoaOptional.isPresent()) {

            PessoaJuridica existente = pessoaOptional.get();

            existente.setNome(pessoaAtualizada.getNome());
            existente.setTelefone(pessoaAtualizada.getTelefone());
            existente.setEmail(pessoaAtualizada.getEmail());
            existente.setEndereco(pessoaAtualizada.getEndereco());

            existente.setCnpj(pessoaAtualizada.getCnpj());
            existente.setRazaoSocial(pessoaAtualizada.getRazaoSocial());

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

    public List<PessoaJuridica> listar() {

        return repository.findAll();

    }
}