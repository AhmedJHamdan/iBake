package com.example.ahmed.ibake;


import android.app.Application;

import com.example.ahmed.ibake.Database.RecipeDB;
import com.example.ahmed.ibake.Database.RecipeDaoHelper;
import com.example.ahmed.ibake.Database.DaoMaster;
import com.example.ahmed.ibake.Database.DaoSession;

public class RecipeApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        daoSession = new DaoMaster(new RecipeDaoHelper(this, RecipeDB.DB_NAME)
                .getWritableDb()).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}