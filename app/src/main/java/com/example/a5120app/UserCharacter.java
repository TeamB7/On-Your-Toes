package com.example.a5120app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserCharacter {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "dob")
    public String dob;
    @ColumnInfo(name = "gender")
    public String gender;
    @ColumnInfo(name = "weight")
    public String weight;
    @ColumnInfo(name = "height")
    public String height;

    public UserCharacter(int id, String name, String dob, String gender, String weight, String height) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
