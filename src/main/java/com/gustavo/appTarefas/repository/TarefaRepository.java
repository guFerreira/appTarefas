package com.gustavo.appTarefas.repository;

import java.util.List;

import com.gustavo.appTarefas.model.Tarefa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{

    @Query
    public List<Tarefa> findByConcluidoTrue();

    @Query
    public List<Tarefa> findByConcluidoFalse();

}
