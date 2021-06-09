package com.gustavo.appTarefas.service;

import java.util.List;

import com.gustavo.appTarefas.model.Tarefa;
import com.gustavo.appTarefas.repository.TarefaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> getTarefasConcluidas(){
        List<Tarefa> tarefas = this.tarefaRepository.findByConcluidoTrue();

        return tarefas;
    }
    
    public List<Tarefa> getTarefasNaoConcluidas(){
        List<Tarefa> tarefas = this.tarefaRepository.findByConcluidoFalse();

        return tarefas;
    }

    public Tarefa criarTarefa(Tarefa tarefa){
        return this.tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(int idTarefa){
        if (tarefaRepository.existsById(idTarefa)){
            this.tarefaRepository.deleteById(idTarefa);
        }
    }

    public void atualizarTarefa(Tarefa tarefa){
        if (this.tarefaRepository.existsById(tarefa.getId())){
            this.tarefaRepository.save(tarefa);
        }
    }

    public Tarefa getTarefaById(int idTarefa){
        Tarefa tarefa = this.tarefaRepository.getById(idTarefa);

        return tarefa;
    }
}
