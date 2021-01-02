package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Salon_Services")

public class SalonServices {

    @PrimaryKey @NonNull
    private String Servicename;

    private int Servicerate;;

    public SalonServices(String Servicename, int Servicerate) {
        this.Servicename = Servicename;
        this.Servicerate = Servicerate;
    }


    public String getServicename() {
        return Servicename;
    }

    public int getServicerate() {
        return Servicerate;
    }
}
