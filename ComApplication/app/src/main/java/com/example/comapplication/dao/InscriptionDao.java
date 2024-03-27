package com.example.comapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.comapplication.MyApplication;
import com.example.comapplication.entity.Inscription;
import com.example.comapplication.entity.User;

import java.util.ArrayList;
import java.util.List;

public class InscriptionDao {

    // Create
    public void saveInscription(Inscription inscription) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_user", inscription.getId_user());
        values.put("id_equipe", inscription.getId_equipe());
        values.put("id_competition", inscription.getId_competition());
        values.put("date", inscription.getDate());
        values.put("statut", inscription.getStatut());
        db.insert("Inscription", null, values);
        db.close();
    }

    // Read (Single Inscription)
    @SuppressLint("Range")
    public Inscription findInscriptionById(int inscriptionId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Inscription", null, "id_inscription=?", new String[]{String.valueOf(inscriptionId)}, null, null, null);

        Inscription inscription = null;
        if (cursor.moveToFirst()) {
            inscription = new Inscription();
            inscription.setId(cursor.getInt(cursor.getColumnIndex("id_inscription")));
            inscription.setId_user(cursor.getInt(cursor.getColumnIndex("id_user")));
            inscription.setId_equipe(cursor.getInt(cursor.getColumnIndex("id_equipe")));
            inscription.setId_competition(cursor.getInt(cursor.getColumnIndex("id_competition")));
            inscription.setDate(cursor.getString(cursor.getColumnIndex("date")));
            inscription.setStatut(cursor.getString(cursor.getColumnIndex("statut")));
        }

        cursor.close();
        db.close();
        return inscription;
    }

    // Read (All Inscriptions)
    @SuppressLint("Range")
    public List<Inscription> findAllInscriptions() {
        List<Inscription> inscriptions = new ArrayList<>();
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Inscription", null);

        if (cursor.moveToFirst()) {
            do {
                Inscription inscription = new Inscription();
                inscription.setId(cursor.getInt(cursor.getColumnIndex("id_inscription")));
                inscription.setId_user(cursor.getInt(cursor.getColumnIndex("id_user")));
                inscription.setId_equipe(cursor.getInt(cursor.getColumnIndex("id_equipe")));
                inscription.setId_competition(cursor.getInt(cursor.getColumnIndex("id_competition")));
                inscription.setDate(cursor.getString(cursor.getColumnIndex("date")));
                inscription.setStatut(cursor.getString(cursor.getColumnIndex("statut")));

                inscriptions.add(inscription);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return inscriptions;
    }

    // Update
    public int updateInscription(Inscription inscription) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_user", inscription.getId_user());
        values.put("id_equipe", inscription.getId_equipe());
        values.put("id_competition", inscription.getId_competition());
        values.put("date", inscription.getDate());
        values.put("statut", inscription.getStatut());

        int rowsAffected = db.update("Inscription", values, "id_inscription=?", new String[]{String.valueOf(inscription.getId())});
        db.close();
        return rowsAffected;
    }

    // Delete
    public void deleteInscription(int inscriptionId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Inscription", "id_inscription=?", new String[]{String.valueOf(inscriptionId)});
        db.close();
    }

}