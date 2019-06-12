package com.example.webservicesapi;

import java.io.Serializable;

public class Treinos implements Serializable {
    private int id = 0;
    private String dia = "";
    private String treino = "";

    public Treinos(int id, String dia, String treino) {
        this.id   = id;
        this.dia = dia;
        this.treino = treino;
    }

    public int getIdTreino() {
        return id;
    }

    public void setIdTreino(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTreino() {
        return treino;
    }

    public void setEmail(String treino) {
        this.treino = treino;
    }
}