package com.example.webservicesapi;

public class Treinos {
    String idtreino;
    String diaSemana;
    String treino;

    public Treinos(String idtreino, String diaSemana, String treino){
        this.idtreino = idtreino;
        this.diaSemana = diaSemana;
        this.treino = treino;
    }
    public String getIdtreino(){return idtreino; }
    public void setIdtreino(String idtreino){this.idtreino = idtreino; }
    public String getDiaSemana(){return diaSemana; }
    public void setDiaSemana(String diaSemana){this.diaSemana = diaSemana; }
    public String getTreino(){return treino; }
    public void setTreino(String treino){this.treino = treino; }
}
