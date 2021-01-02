package com.example.qvid;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Salon")


public class Salon {

    @PrimaryKey @NonNull
    private int Salonid;

    private String Salonname;

    public Salon(int Salonid, String Salonname) {
        this.Salonid = Salonid;
        this.Salonname = Salonname;
    }

    public int getSalonid() {
        return Salonid;
    }

    public String getSalonname() {
        return Salonname;
    }


}