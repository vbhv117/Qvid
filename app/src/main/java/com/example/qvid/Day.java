package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Day")

public class Day {

    @PrimaryKey
    private int Dayid;

    private String Dayname;

    public Day(int Dayid, String Dayname) {
        this.Dayid = Dayid;
        this.Dayname = Dayname;
    }

    public void setDayid(int Dayid) {
        this.Dayid = Dayid;
    }

    public void setDayname(String Dayname) {
        this.Dayname = Dayname;
    }

    public int getDayid() {
        return Dayid;
    }

    public String getDayname() {
        return Dayname;
    }

}
