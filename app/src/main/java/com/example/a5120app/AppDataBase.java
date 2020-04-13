package com.example.a5120app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserCharacter.class, Task.class, Accessory.class}, version = 4)

public abstract class AppDataBase extends RoomDatabase{
    private static volatile AppDataBase INSTANCE;

    static AppDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "customer_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CharacterDao characterDao();
}
