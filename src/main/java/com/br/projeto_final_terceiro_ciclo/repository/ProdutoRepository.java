package com.br.projeto_final_terceiro_ciclo.repository;

import com.br.projeto_final_terceiro_ciclo.controller.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}