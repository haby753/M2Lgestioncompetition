package com.example.comapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.comapplication.MyApplication;
import com.example.comapplication.entity.Club;

import java.util.ArrayList;
import java.util.List;

public class ClubDao {

    // Create
    public void saveClub(Club club) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", club.getNom());
        values.put("address", club.getAdresse());
        values.put("contact", club.getContact());
        values.put("categories", club.getCategorie());
        db.insert("Club", null, values);
        //db.close();
    }

    // Read (Single Club)
    @SuppressLint("Range")
    public Club findClubById(int clubId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Club", null, "id=?", new String[]{String.valueOf(clubId)}, null, null, null);

        Club club = null;
        if (cursor.moveToFirst()) {
            club = new Club();
            club.setId(cursor.getInt(cursor.getColumnIndex("id")));
            club.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            club.setAdresse(cursor.getString(cursor.getColumnIndex("address")));
            club.setContact(cursor.getString(cursor.getColumnIndex("contact")));
            club.setCategorie(cursor.getString(cursor.getColumnIndex("categories")));
        }

        cursor.close();
        //db.close();
        return club;
    }

    // Read (All Clubs)
    @SuppressLint("Range")
    public List<Club> findAllClubs() {
        List<Club> clubs = new ArrayList<>();
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Club", null);

        if (cursor.moveToFirst()) {
            do {
                Club club = new Club();
                club.setId(cursor.getInt(cursor.getColumnIndex("id")));
                club.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                club.setAdresse(cursor.getString(cursor.getColumnIndex("address")));
                club.setContact(cursor.getString(cursor.getColumnIndex("contact")));
                club.setCategorie(cursor.getString(cursor.getColumnIndex("categories")));

                clubs.add(club);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        return clubs;
    }

    // Update
    public int updateClub(Club club) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", club.getNom());
        values.put("address", club.getAdresse());
        values.put("contact", club.getContact());
        values.put("categories", club.getCategorie());

        int rowsAffected = db.update("Club", values, "id=?", new String[]{String.valueOf(club.getId())});
       // db.close();
        return rowsAffected;
    }

    // Delete
    public void deleteClub(int clubId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Club", "id=?", new String[]{String.valueOf(clubId)});
       // db.close();
    }
}