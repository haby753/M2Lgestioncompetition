package com.example.comapplication.entity;

import java.io.Serializable;

public class Club  implements Serializable {

    private int id_club;
    private String nom ;
    private String  adresse ;
    private String contact ;
    private String categorie ;

    public int getId() {
        return id_club;
    }

    public void setId(int id_clud) {
        this.id_club = id_clud;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}

