package com.example.comapplication;

import android.app.Application;
import com.example.comapplication.dao.DatabaseHelper;

public class MyApplication extends Application {
    private static DatabaseHelper dbHelper ;
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialiser votre base de données ici
        dbHelper = new DatabaseHelper(this);
        dbHelper.getWritableDatabase();

    }
    public static DatabaseHelper getDbHelper() {
        return dbHelper;
    }
    public static void setDbHelper(DatabaseHelper dbHelper) {
        MyApplication.dbHelper = dbHelper;
    }
}

