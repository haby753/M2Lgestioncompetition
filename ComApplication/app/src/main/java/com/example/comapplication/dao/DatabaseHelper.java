package com.example.comapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nom de la base de données
    private static final String DATABASE_NAME = "user_roles.db";
    // Version de la base de données
    private static final int DATABASE_VERSION = 2;
    // Commandes SQL pour créer les tables User et Role
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE User (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "prenom TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL," +
                    "roleId INTEGER," +
                    "FOREIGN KEY(roleId) REFERENCES Role(id),"+
                    "FOREIGN KEY(id_equipe) REFERENCES Equipe(id))";

    private static final String CREATE_TABLE_ROLE =
            "CREATE TABLE Role (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "roleName TEXT NOT NULL)";

    private static final String CREATE_TABLE_INSCRIPTION =
            "CREATE TABLE Inscription (" +
                    "id_inscription INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_user INTEGER NOT NULL," +
                    "id_competition INTEGER NOT NULL," +
                    "date TEXT NOT NULL," +
                    "statut TEXT NOT NULL," +
                    "FOREIGN KEY(id_user) REFERENCES User(id)," +
                    "FOREIGN KEY(id_competition) REFERENCES Competition(id))";

    private static final String CREATE_TABLE_EQUIPE =
            "CREATE TABLE Equipe (" +
                    "id_equipe INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_club INTEGER," +
                    "nom TEXT NOT NULL," +
                    "FOREIGN KEY(id_club) REFERENCES Club(id))";

    private static final String CREATE_TABLE_COMPETITION =
            "CREATE TABLE Competition (" +
                    "id_competition INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "description TEXT NOT NULL," +
                    "datededebut TEXT NOT NULL," +
                    "datedefin TEXT NOT NULL," +
                    "nombredeparticipant INTEGER NOT NULL)";

    private static final String CREATE_TABLE_CLUB =
            "CREATE TABLE Club (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "adresse TEXT NOT NULL," +
                    "contact TEXT NOT NULL," +
                    "categorie TEXT NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création des tables User et Role
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if(oldVersion <2){

        db.execSQL(CREATE_TABLE_CLUB);
        db.execSQL(CREATE_TABLE_COMPETITION);
        db.execSQL(CREATE_TABLE_INSCRIPTION);
        db.execSQL(CREATE_TABLE_EQUIPE);

        // Insertion des données initiales dans les tables Club et Competition
        insertClub(db);
        insertCompetition(db);
        insertEquipe(db);

      }
    }

    private void insertCompetition(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Competition (nom, description, datededebut, datedefin, nombredeparticipant) VALUES ('Compétition de football', 'Description de la compétition', '2024-05-01', '2024-05-15', 100)");
        db.execSQL("INSERT INTO Competition (nom, description, datededebut, datedefin, nombredeparticipant) VALUES ('Compétition de basketball', 'Description de la compétition', '2024-06-01', '2024-06-15', 50)");
        db.execSQL("INSERT INTO Competition (nom, description, datededebut, datedefin, nombredeparticipant) VALUES ('Compétition de tennis', 'Description de la compétition', '2024-07-01', '2024-07-15', 80)");
        db.execSQL("INSERT INTO Competition (nom, description, datededebut, datedefin, nombredeparticipant) VALUES ('Compétition de natation', 'Description de la compétition de natation', '2024-08-01', '2024-08-15', 120)");
        db.execSQL("INSERT INTO Competition (nom, description, datededebut, datedefin, nombredeparticipant) VALUES ('Compétition athlétisme', 'Description de la compétition athlétisme', '2024-09-01', '2024-09-15', 150)");
        db.execSQL("INSERT INTO Competition (nom, description, datededebut, datedefin, nombredeparticipant) VALUES ('Compétition de judo', 'Description de la compétition judo', '2024-10-01', '2024-12-15', 70)");
    }
    private void insertEquipe(SQLiteDatabase db) {
        for (int i = 1; i <= 3; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (1, 'Équipe " + i + " du Club de football A')");
        }
        for (int i = 1; i <= 4; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (2, 'Équipe " + i + " du Club de football B')");
        }
        for (int i = 1; i <= 2; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (3, 'Équipe " + i + " du Club de basketball C')");
        }
        for (int i = 1; i <= 5; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (4, 'Équipe " + i + " du Club de tennis D')");
        }
        for (int i = 1; i <= 3; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (5, 'Équipe " + i + " du Club de natation E')");
        }
        for (int i = 1; i <= 2; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (6, 'Équipe " + i + " du Club d athlétisme F')");
        }
        for (int i = 1; i <= 3; i++) {
            db.execSQL("INSERT INTO Equipe (id_club, nom) VALUES (7, 'Équipe " + i + " du Club de judo G')");

        }
    }

    private void insertClub(SQLiteDatabase db) {

        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club de football A', '123 Rue du Stade', 'contact@clubfootball.com', 'Sport')");
        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club de football B', '456 Avenue du Stade', 'contact@clubfootballB.com', 'Sport')");
        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club de basketball C', '789 Boulevard du Stade', 'contact@clubbasketballC.com', 'Sport')");
        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club de tennis D', '101 Rue du Tennis', 'contact@clubtennisD.com', 'Sport')");
        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club de natation E', '202 Avenue de la Piscine', 'contact@clubnatationE.com', 'Sport')");
        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club d athletisme D', '101 Rue d athlete ', 'contact@clubathleteD.com', 'Sport')");
        db.execSQL("INSERT INTO Club (nom, adresse, contact, categorie) VALUES ('Club de judo E', '202 Avenue du judo', 'contact@clubjudoE.com', 'Sport')");
    }
}
