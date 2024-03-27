package com.example.comapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nom de la base de données
    private static final String DATABASE_NAME = "user_roles.db";


    // Version de la base de données
    private static final int DATABASE_VERSION = 1;

    // Commandes SQL pour créer les tables User et Role
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE User (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "prenom TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL," +
                    "roleId INTEGER," +
                    "FOREIGN KEY(roleId) REFERENCES Role(id))";

    private static final String CREATE_TABLE_ROLE =
            "CREATE TABLE Role (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "roleName TEXT NOT NULL)";




    private static final String CREATE_TABLE_INSCRIPTION =
            "CREATE TABLE Inscription (" +
                    "id_inscription INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_user INTEGER NOT NULL," +
                    "id_equipe INTEGER NOT NULL," +
                    "id_competition INTEGER NOT NULL," +
                    "date TEXT NOT NULL," +
                    "statut TEXT NOT NULL," +
                    "FOREIGN KEY(id_user) REFERENCES User(id)," +
                    "FOREIGN KEY(id_equipe) REFERENCES Equipe(id)," +
                    "FOREIGN KEY(id_competition) REFERENCES Competition(id))";


    private static final String CREATE_TABLE_EQUIPE =
            "CREATE TABLE Equipe (" +
                    "id_equipe INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL)";


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
        db.execSQL(CREATE_TABLE_INSCRIPTION);
        db.execSQL(CREATE_TABLE_EQUIPE);
        db.execSQL(CREATE_TABLE_COMPETITION);
        db.execSQL(CREATE_TABLE_CLUB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ici vous pouvez gérer les mises à jour de votre schéma de base de données pour les nouvelles versions
    }
}
