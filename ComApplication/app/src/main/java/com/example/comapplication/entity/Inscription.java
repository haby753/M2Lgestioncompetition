package com.example.comapplication.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscription implements Serializable {

    private int id_inscription;
    private User id_user ;
    private Competition id_competition;
    private LocalDate datedecompetition;
    private String statut ;


    public int getId() {
        return id_inscription;
    }

    public void setId(int id) {
        this.id_inscription = id;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }



    public Competition getId_competition() {
        return id_competition;
    }

    public void setId_competition(Competition id_competition) {
        this.id_competition = id_competition;
    }

    public LocalDate getDate() {
        return datedecompetition;
    }

    public void setDate(LocalDate date) {
        this.datedecompetition = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setId_user(int user) {
    }

    public void setId_competition(int idCompetition) {
    }

    public void setDate(String datedecompetition) {
    }
}
