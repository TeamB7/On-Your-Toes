package com.example.a5120app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM usercharacter")
    List<UserCharacter> getAll();

    @Insert
    long insert(UserCharacter userCharacter);

    @Query("SELECT * FROM usercharacter WHERE id = :id LIMIT 1")
    UserCharacter findById(int id);

}
