package com.example.qvid;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class QvidRepository {
    private QvidDao qvidDao;
    private LiveData<List<User>> login;
    private LiveData<List<Salon>> salonid;
    private LiveData<List<SalonServices>> allSalonServices;
    private LiveData<List<Salon>> allSalons;
    private LiveData<List<Integer>> dayid;
    private LiveData<List<Integer>> slotid;
    private List<String> slotname;
    private LiveData<List<Integer>> slot_id;
    private LiveData<List<BookingDetails>> alluserdetails;
    private LiveData<List<BookingDetails>> alladmindetails;
    private List<BookingDetails> allupdatedetails;
    private List<Intermediate> allintermediate;

    public QvidRepository(Application application) {
        QvidDatabase database = QvidDatabase.getInstance(application);
        qvidDao = database.qvidDao();
        allSalonServices = qvidDao.getAllSalonServices();
        allSalons = qvidDao.getAllSalons();
    }

    public void insert_ss(SalonServices salonServices) {
        new InsertNoteAsyncTask(qvidDao).execute(salonServices);

    }

    public void update_ss(SalonServices salonServices) {
        new UpdateNoteAsyncTask(qvidDao).execute(salonServices);

    }

    public void delete_intermediate(Intermediate intermediate){
        new DeleteIntermediateAsyncTask(qvidDao).execute(intermediate);
    }
    private static class DeleteIntermediateAsyncTask extends AsyncTask<Intermediate, Void, Void> {
        private QvidDao qvidDao;

        private DeleteIntermediateAsyncTask(QvidDao qvidDao) {
            this.qvidDao = qvidDao;
        }


        @Override
        protected Void doInBackground(Intermediate... intermediates) {
            qvidDao.delete_intermediate(intermediates[0]);
            return null;
        }
    }
    public void update_bd(BookingDetails bookingDetails){
        new UpdateBookingDetailsAsyncTask(qvidDao).execute(bookingDetails);
    }

    private static class UpdateBookingDetailsAsyncTask extends AsyncTask<BookingDetails,Void,Void> {
        private QvidDao qvidDao;

        private UpdateBookingDetailsAsyncTask(QvidDao qvidDao){

            this.qvidDao = qvidDao;
        }

        @Override
        protected Void doInBackground(BookingDetails... bookingDetails) {
            qvidDao.update_bd(bookingDetails[0]);
            return null;
        }


    }

    public LiveData<List<SalonServices>> getAllSalonServices() {
        return allSalonServices;
    }

    public LiveData<List<Salon>> getAllSalons() {
        return allSalons;
    }

    public LiveData<List<Integer>> getDayid(String weekday) {
        dayid = qvidDao.getdayid(weekday);
        return dayid;
    }
    public LiveData<List<BookingDetails>> getAlluserdetails(String user){
        alluserdetails = qvidDao.get_user_data(user);
        return alluserdetails;
    }

    public LiveData<List<BookingDetails>> getAlladmindetails(int salon_id){
        alladmindetails = qvidDao.get_admin_data(salon_id);
        return alladmindetails;
    }

    public LiveData<List<Integer>> getSlotid(int salonid, int dayid) {
        slotid = qvidDao.getslotid(salonid, dayid);
        return slotid;
    }

    public List<Intermediate> getIntermediate(int salonid, int dayid) {
        allintermediate = qvidDao.get_intermediate(salonid, dayid);
        return allintermediate;
    }


    public List<String> getSlotname(int slotid) {
        slotname = qvidDao.getslotname(slotid);
        return slotname;
    }



    public LiveData<List<Integer>> get_Slot_id(String slot_name) {
        slot_id = qvidDao.get_slot_id(slot_name);
        return slot_id;
    }

    public List<BookingDetails> get_update_details(int salon_id, String day_name ){
        allupdatedetails = qvidDao.get_update_details(salon_id,day_name);
        return allupdatedetails;
    }



    private static class InsertNoteAsyncTask extends AsyncTask<SalonServices, Void, Void> {
        private QvidDao qvidDao;

        private InsertNoteAsyncTask(QvidDao qvidDao) {
            this.qvidDao = qvidDao;
        }


        @Override
        protected Void doInBackground(SalonServices... salonServices) {
            qvidDao.insert_ss(salonServices[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<SalonServices, Void, Void> {
        private QvidDao qvidDao;

        private UpdateNoteAsyncTask(QvidDao qvidDao) {
            this.qvidDao = qvidDao;
        }


        @Override
        protected Void doInBackground(SalonServices... salonServices) {
            qvidDao.update_ss(salonServices[0]);
            return null;
        }
    }


    public void insert_user(User user) {

        new InsertUserAsyncTask(qvidDao).execute(user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private QvidDao qvidDao;

        private InsertUserAsyncTask(QvidDao qvidDao) {

            this.qvidDao = qvidDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            qvidDao.insert_user(users[0]);
            return null;
        }


    }

    public void insert_bd(BookingDetails bookingDetails) {

        new InsertBookingDetailsAsyncTask(qvidDao).execute(bookingDetails);
    }

    private static class InsertBookingDetailsAsyncTask extends AsyncTask<BookingDetails, Void, Void> {
        private QvidDao qvidDao;


        private InsertBookingDetailsAsyncTask(QvidDao qvidDao) {

            this.qvidDao = qvidDao;
        }

        @Override
        protected Void doInBackground(BookingDetails... bookingDetails) {
            qvidDao.insert_bd(bookingDetails[0]);
            return null;
        }
    }

        public void insert_intermediate(Intermediate intermediate) {

            new InsertIntermediateAsyncTask(qvidDao).execute(intermediate);
        }

        private static class InsertIntermediateAsyncTask extends AsyncTask<Intermediate, Void, Void> {
            private QvidDao qvidDao;

            private InsertIntermediateAsyncTask(QvidDao qvidDao) {

                this.qvidDao = qvidDao;
            }

            @Override
            protected Void doInBackground(Intermediate... intermediates) {
                qvidDao.insert_intermediate(intermediates[0]);
                return null;
            }


        }

        public LiveData<List<User>> getLogin(String mail) {
            login = qvidDao.getlogin(mail);
            return login;

        }

        public LiveData<List<Salon>> getSalonid(String salon_name){
        salonid = qvidDao.getsalonid(salon_name);
        return salonid;
        }

}
