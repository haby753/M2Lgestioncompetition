package com.example.comapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.comapplication.MyApplication;
import com.example.comapplication.entity.Competition;

import java.util.ArrayList;
import java.util.List;

public class CompetitionDao {

    // Create
    public void saveCompetition(Competition competition) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", competition.getNom());
        values.put("description", competition.getDescription());
        values.put("datededebut", competition.getDatededebut());
        values.put("datedefin", competition.getDatedefin());
        values.put("nombredeparticipant", competition.getNombredeparticipant());
        db.insert("Competition", null, values);
        db.close();
    }

    // Read (Single Competition)
    @SuppressLint("Range")
    public Competition findCompetitionById(int competitionId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Competition", null, "id_competition=?", new String[]{String.valueOf(competitionId)}, null, null, null);

        Competition competition = null;
        if (cursor.moveToFirst()) {
            competition = new Competition();
            competition.setId(cursor.getInt(cursor.getColumnIndex("id_competition")));
            competition.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            competition.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            competition.setDatededebut(cursor.getString(cursor.getColumnIndex("datededebut")));
            competition.setDatedefin(cursor.getString(cursor.getColumnIndex("datedefin")));
            competition.setNombredeparticipant(cursor.getInt(cursor.getColumnIndex("nombredeparticipant")));
        }

        cursor.close();
        db.close();
        return competition;
    }

    // Read (All Competitions)
    @SuppressLint("Range")
    public List<Competition> findAllCompetitions() {
        List<Competition> competitions = new ArrayList<>();
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Competition", null);

        if (cursor.moveToFirst()) {
            do {
                Competition competition = new Competition();
                competition.setId(cursor.getInt(cursor.getColumnIndex("id_competition")));
                competition.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                competition.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                competition.setDatededebut(cursor.getString(cursor.getColumnIndex("datededebut")));
                competition.setDatedefin(cursor.getString(cursor.getColumnIndex("datedefin")));
                competition.setNombredeparticipant(cursor.getInt(cursor.getColumnIndex("nombredeparticipant")));

                competitions.add(competition);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return competitions;
    }

    // Update
    public int updateCompetition(Competition competition) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", competition.getNom());
        values.put("description", competition.getDescription());
        values.put("datededebut", competition.getDatededebut());
        values.put("datedefin", competition.getDatedefin());
        values.put("nombredeparticipant", competition.getNombredeparticipant());

        int rowsAffected = db.update("Competition", values, "id_competition=?", new String[]{String.valueOf(competition.getId())});
        db.close();
        return rowsAffected;
    }

    // Delete
    public void deleteCompetition(int competitionId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Competition", "id_competition=?", new String[]{String.valueOf(competitionId)});
        db.close();
    }
}