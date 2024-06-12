package com.example.comapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.comapplication.MyApplication;
import com.example.comapplication.entity.Equipe;

import java.util.ArrayList;
import java.util.List;

public class EquipeDao {

    // Create
    public void saveEquipe(Equipe equipe) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", equipe.getNom());
        db.insert("Equipe", null, values);
       // db.close();
    }

    // Read (Single Equipe)
    @SuppressLint("Range")
    public Equipe findEquipeById(int equipeId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Equipe", null, "id_equipe=?", new String[]{String.valueOf(equipeId)}, null, null, null);

        Equipe equipe = null;
        if (cursor.moveToFirst()) {
            equipe = new Equipe();
            equipe.setId(cursor.getInt(cursor.getColumnIndex("id_equipe")));
            equipe.setNom(cursor.getString(cursor.getColumnIndex("nom")));
        }

        cursor.close();
      //  db.close();
        return equipe;
    }

    // Read (All Equipes)
    @SuppressLint("Range")
    public List<Equipe> findAllEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Equipe", null);

        if (cursor.moveToFirst()) {
            do {
                Equipe equipe = new Equipe();
                equipe.setId(cursor.getInt(cursor.getColumnIndex("id_equipe")));
                equipe.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                equipes.add(equipe);
            } while (cursor.moveToNext());
        }

        cursor.close();
       // db.close();

        return equipes;
    }

    // Update
    public int updateEquipe(Equipe equipe) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", equipe.getNom());

        int rowsAffected = db.update("Equipe", values, "id_equipe=?", new String[]{String.valueOf(equipe.getId())});
     //   db.close();
        return rowsAffected;
    }

    // Delete
    public void deleteEquipe(int equipeId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Equipe", "id_equipe=?", new String[]{String.valueOf(equipeId)});
      //  db.close();
    }
}