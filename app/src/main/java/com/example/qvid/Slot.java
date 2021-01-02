package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Slot")

public class Slot {

    @PrimaryKey
    private int Slotid;

    private String Slotname;

    public Slot(int Slotid, String Slotname) {
        this.Slotid = Slotid;
        this.Slotname = Slotname;
    }

    public int getSlotid() {

        return  Slotid;
    }

    public String getSlotname()
    {
        return Slotname;
    }
}
