package com.example.comapplication.entity;

import java.io.Serializable;

public class Equipe implements Serializable {

    private int id_equipe;
    private String nom;

    public Club getId_club() {
        return id_club;
    }

    public void setId_club(Club id_club) {
        this.id_club = id_club;
    }

    private Club id_club;

    public int getId() {
        return id_equipe;
    }

    public void setId(int id) {
        this.id_equipe = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
// ajoutez les getters et les setters

}
