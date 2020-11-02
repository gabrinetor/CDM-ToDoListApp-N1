package com.example.todolist_app;

public class Tarefa {

    private int id;
    private String tarefa;
    private String date;

    public Tarefa() {
    }

    public Tarefa(String tarefa, String date) {
        this.tarefa = tarefa;
        this.date = date;
    }

    public Tarefa(int id, String tarefa, String date) {
        this.id = id;
        this.tarefa = tarefa;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
