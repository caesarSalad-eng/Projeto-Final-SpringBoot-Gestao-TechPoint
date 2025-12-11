package com.br.projeto_final_terceiro_ciclo.repository;

import com.br.projeto_final_terceiro_ciclo.controller.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {



}
