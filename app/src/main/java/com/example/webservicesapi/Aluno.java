package com.example.webservicesapi;

import java.io.Serializable;

public class Aluno implements Serializable {

    private int id;
    private String nome;
    private String email;
    private String password;
    private String profile;

    public Aluno(){};
    public Aluno(int id, String nome, String email, String password, String profile) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){return password; }
    public void setPassword(String password){this.password = password; }

    public String getProfile(){return profile; }
    public void setProfile(String profile){this.profile = profile; }
}
