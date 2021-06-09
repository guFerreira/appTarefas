package com.gustavo.appTarefas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 120)
    @Size(min = 2, max = 120, message = "O Título não pode possuir menos que 2 caracteres e mais que 120")
    private String titulo;

    @Column(columnDefinition="TEXT")
    private String descricao;

    private boolean concluido;

    public Tarefa(){

    }

    public Tarefa(int id, String titulo, String descricao){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public boolean isConcluido() {
        return this.concluido;
    }

    public boolean getConcluido() {
        return this.concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

}
