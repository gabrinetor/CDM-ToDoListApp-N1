package com.example.todolist_app;

public class Tarefa {

    private int id;
    private String tarefa;
    private String dataMarcada;

    public Tarefa() {
    }

    public Tarefa(String tarefa, String dataMarcada) {
        this.tarefa = tarefa;
        this.dataMarcada = dataMarcada;
    }

    public Tarefa(int id, String tarefa, String dataMarcada) {
        this.id = id;
        this.tarefa = tarefa;
        this.dataMarcada = dataMarcada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(String dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    @Override
    public String toString() {
        return tarefa + " - " + dataMarcada;
    }
}
