package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookingDetails {
    public int getBookingId() {
        return BookingId;
    }

    public String getUserId() {
        return UserId;
    }

    public int getSalonId() {
        return SalonId;
    }

    public String getSalonName() {
        return SalonName;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public int getServiceRate() {
        return ServiceRate;
    }

    public String getDayName() {
        return DayName;
    }

    public String getSlotName() {
        return SlotName;
    }

    public String getUsername() {
        return Username;
    }

    public String getStatus() {
        return Status;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int BookingId;

    private String UserId;
    private int SalonId;
    private String SalonName;
    private String ServiceName;
    private int ServiceRate;
    private String DayName;
    private String SlotName;
    private String Username;
    private String Status;


    public BookingDetails(String UserId, int SalonId, String SalonName, String ServiceName, int ServiceRate, String DayName, String SlotName, String Username, String Status) {
        this.UserId = UserId;
        this.SalonId = SalonId;
        this.SalonName = SalonName;
        this.ServiceName = ServiceName;
        this.ServiceRate = ServiceRate;
        this.DayName = DayName;
        this.SlotName = SlotName;
        this.Username = Username;
        this.Status = Status;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setSalonId(int salonId) {
        SalonId = salonId;
    }

    public void setSalonName(String salonName) {
        SalonName = salonName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public void setServiceRate(int serviceRate) {
        ServiceRate = serviceRate;
    }

    public void setDayName(String dayName) {
        DayName = dayName;
    }

    public void setSlotName(String slotName) {
        SlotName = slotName;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setStatus(String status) {
        Status = status;
    }
}





