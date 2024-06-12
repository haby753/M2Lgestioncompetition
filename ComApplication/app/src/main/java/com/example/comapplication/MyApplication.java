package com.example.comapplication;

import android.app.Application;

import com.example.comapplication.dao.CompetitionDao;
import com.example.comapplication.dao.DatabaseHelper;
import com.example.comapplication.dao.RoleDao;
import com.example.comapplication.entity.Competition;
import com.example.comapplication.entity.Role;

import java.util.Arrays;

public class MyApplication extends Application {
    private static DatabaseHelper dbHelper ;
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialiser votre base de données ici
        dbHelper = new DatabaseHelper(this);
        if(RoleDao.findRoleById(1) == null)
        {
          Role  role = new Role();
          role.setRoleName("ROLE_USER");
          RoleDao.saveRole(role);}

        if(RoleDao.findRoleById(2) == null)
        {
            Role  role1 = new Role();
            role1.setRoleName("ROLE_ADMIN");
            RoleDao.saveRole(role1);}
        dbHelper.getWritableDatabase();
    }
    public static DatabaseHelper getDbHelper() {
        return dbHelper;
    }
    public static void setDbHelper(DatabaseHelper dbHelper) {
        MyApplication.dbHelper = dbHelper;
    }


}

