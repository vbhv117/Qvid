package com.example.qvid;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface QvidDao {

    @Insert
    void insert_ss(SalonServices salonServices);
    @Insert
    void insert_bd(BookingDetails bookingDetails);
    @Insert
    void insert_s(Salon salon);
    @Insert
    void insert_day(Day day);
    @Insert
    void insert_slot(Slot slot);
    @Insert
    void insert_user(User user);
    @Insert
    void insert_intermediate(Intermediate intermediate);

    @Update
    void update_ss(SalonServices salonServices);

    @Update
    void update_bd(BookingDetails bookingDetails);

    @Delete
    void delete_ss(SalonServices salonServices);
    @Delete
    void delete_bd(BookingDetails bookingDetails);
    @Delete
    void delete_s(Salon salon);

    @Delete
    void delete_intermediate(Intermediate intermediate);



    @Query("SELECT * FROM Salon_Services")
    LiveData<List<SalonServices>> getAllSalonServices();


    @Query("SELECT * FROM Salon")
    LiveData<List<Salon>> getAllSalons();

    @Query("SELECT * FROM User WHERE email_id=:mail")
    LiveData<List<User>> getlogin(String mail);

    @Query("Select * FROM Salon WHERE Salonname =:salon_name")
    LiveData<List<Salon>> getsalonid(String salon_name);

    @Query("SELECT Dayid FROM Day WHERE Dayname =:weekday")
    LiveData<List<Integer>> getdayid(String weekday);

    @Query("SELECT Slotid FROM Intermediate WHERE Salonid =:salon_id AND Dayid =:day_id")
    LiveData<List<Integer>> getslotid(int salon_id, int day_id);

    @Query("SELECT Slotname FROM Slot WHERE Slotid =:slot_id")
    List<String> getslotname(int slot_id);

    @Query("SELECT Slotid FROM Slot WHERE Slotname=:Slotname")
    LiveData<List<Integer>> get_slot_id(String Slotname);

    @Query("SELECT * FROM BookingDetails WHERE UserId =:User")
    LiveData<List<BookingDetails>> get_user_data(String User);

    @Query("SELECT * FROM BookingDetails WHERE SalonId =:salon_id")
    LiveData<List<BookingDetails>> get_admin_data(int salon_id);

    @Query("SELECT * FROM BOOKINGDETAILS WHERE SalonId=:salon_id AND DayName=:day_name")
    List<BookingDetails> get_update_details(int salon_id,String day_name);

    @Query("SELECT * FROM Intermediate WHERE Salonid =:salon_id AND Dayid =:day_id")
    List<Intermediate> get_intermediate(int salon_id, int day_id);


}