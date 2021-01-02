package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Salon.class, parentColumns = "Salonid", childColumns = "Salonid"),
@ForeignKey(entity = Day.class, parentColumns = "Dayid", childColumns = "Dayid"),
@ForeignKey(entity = Slot.class, parentColumns = "Slotid", childColumns = "Slotid")},
primaryKeys = {"Salonid", "Dayid", "Slotid"})

public class Intermediate {

    private int Salonid;
    private int Dayid;
    private int Slotid;

    public void setSalonid(int salonid) {
        Salonid = salonid;
    }

    public void setDayid(int dayid) {
        Dayid = dayid;
    }

    public void setSlotid(int slotid) {
        Slotid = slotid;
    }

    public Intermediate(int Salonid, int Dayid, int Slotid) {
        this.Salonid = Salonid;
        this.Dayid = Dayid;
        this.Slotid = Slotid;
    }

    public int getSalonid() {
        return Salonid;
    }

    public int getDayid() {
        return  Dayid;

    }

    public  int getSlotid() {
        return Slotid;
    }
}


