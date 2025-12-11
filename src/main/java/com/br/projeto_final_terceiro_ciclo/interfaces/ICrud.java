package com.br.projeto_final_terceiro_ciclo.interfaces;
import java.util.List;

public interface ICrud<T> {
    void cadastrar(T obj);
    T consultar(int id);
    void atualizar(T obj);
    void deletar(int id);
    List<T> listar();
}
