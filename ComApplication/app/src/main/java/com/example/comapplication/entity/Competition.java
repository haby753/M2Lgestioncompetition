package com.example.comapplication.entity;

import java.io.Serializable;

public class Competition implements Serializable {

    private int id ;
    private String nom ;
    private String description;
    private String datededebut ;
    private String datedefin ;
    private Number nombredeparticipant ;

    public int getId() {
        return id;
    }

    public void setId(int id_competition) {
        this.id = id_competition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatededebut() {
        return datededebut;
    }

    public void setDatededebut(String datededebut) {
        this.datededebut = datededebut;
    }

    public String getDatedefin() {
        return datedefin;
    }

    public void setDatedefin(String datedefin) {
        this.datedefin = datedefin;
    }

    public String getNombredeparticipant() {
        return nombredeparticipant.toString();
    }

    public void setNombredeparticipant(Number nombredeparticipant) {
        this.nombredeparticipant = nombredeparticipant;
    }


}

