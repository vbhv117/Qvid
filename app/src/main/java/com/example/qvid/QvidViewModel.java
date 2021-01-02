package com.example.qvid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
public class QvidViewModel extends AndroidViewModel {

    private QvidRepository repository;
    private LiveData<List<User>> login;
    private LiveData<List<Salon>> salonid;
    private LiveData<List<SalonServices>> allSalonServices;
    private LiveData<List<Salon>> allSalons;
    private LiveData<List<Integer>> dayid;
    private LiveData<List<Integer>> slotid;
    private LiveData<List<Integer>> slot_id;
    private LiveData<List<BookingDetails>> alluserdetails;
    private LiveData<List<BookingDetails>> alladmindetails;
    private List<BookingDetails> allupdatedetails;
    private List<Intermediate> allintermediate;

    public QvidViewModel(@NonNull Application application) {
        super(application);
        repository = new QvidRepository(application);
        allSalonServices = repository.getAllSalonServices();
        allSalons= repository.getAllSalons();
    }

    public void insert_ss(SalonServices salonServices) {
        repository.insert_ss(salonServices);
    }
    public void update_ss(SalonServices salonServices) {
        repository.update_ss(salonServices);
    }
    public LiveData<List<SalonServices>> getAllSalonServices() {
        return allSalonServices;
    }

    public LiveData<List<Salon>> getAllSalons() {
        return allSalons;
    }

    public LiveData<List<BookingDetails>> getAlluserdetails(String user){
        alluserdetails = repository.getAlluserdetails(user);
        return alluserdetails;
    }
    public LiveData<List<BookingDetails>> getAlladmindetails(int salon_id){
        alladmindetails = repository.getAlladmindetails(salon_id);
        return alladmindetails;
    }

//    public  List<BookingDetails> get_update_details(int salon_id, String day_id){
//        allupdatedetails = repository.get_update_details(salon_id, day_id);
//        return allupdatedetails;
//    }


    public LiveData<List<Integer>> getDayid(String weekday)
    {
        dayid = repository.getDayid(weekday);
        return dayid;
    }

    public LiveData<List<Integer>> getSlotid(int salonid, int dayid)
    {
        slotid = repository.getSlotid(salonid, dayid);
        return slotid;
    }
//
//    public List<Intermediate> getIntermediate(int salonid, int dayid)
//    {
//        allintermediate = repository.getIntermediate(salonid, dayid);
//        return allintermediate;
//    }

    public LiveData<List<Integer>> get_Slot_id(String slot_name)
    {
        slot_id = repository.get_Slot_id(slot_name);
        return slot_id;
    }

    public void update_bd(BookingDetails bookingDetails){
        repository.update_bd(bookingDetails);
    }

    public void delete_intermediate(Intermediate intermediate){
        repository.delete_intermediate(intermediate);
    }

    public void insert_user(User user){

        repository.insert_user(user);
    }
    public LiveData<List<User>> getLogin(String mail){
        login = repository.getLogin(mail);
        return login;
    }

    public LiveData<List<Salon>> getSalonid(String salon_name){
        salonid = repository.getSalonid(salon_name);
        return salonid;
    }

    public void insert_bd(BookingDetails bookingDetails){

        repository.insert_bd(bookingDetails);
    }

    public void insert_intermediate(Intermediate intermediate){

        repository.insert_intermediate(intermediate);
    }
}
