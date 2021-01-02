package com.example.qvid;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SalonServices.class, BookingDetails.class, Salon.class, User.class, Day.class, Slot.class, Intermediate.class}, version = 1)

public abstract class QvidDatabase extends RoomDatabase {

    private static QvidDatabase instance;

    public abstract QvidDao
    qvidDao();

    public static  synchronized QvidDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QvidDatabase.class, "qvid_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack


                    )
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private QvidDao qvidDao;

        private PopulateDbAsyncTask(QvidDatabase db)
        {
            qvidDao = db.qvidDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            qvidDao.insert_ss(new SalonServices("Haircut-Men",200));
            qvidDao.insert_ss(new SalonServices("Haircut-Women",900));
            qvidDao.insert_ss(new SalonServices("Beard Trim",100));
            qvidDao.insert_ss(new SalonServices("Beard Shave",80));
            qvidDao.insert_ss(new SalonServices("Haircut and Beard Trim",280));
            qvidDao.insert_ss(new SalonServices("Haircut and Beard Shave",250));
            qvidDao.insert_ss(new SalonServices("Straightening",2000));
            qvidDao.insert_ss(new SalonServices("Keratin Treatment",7000));
            qvidDao.insert_ss(new SalonServices("Facial-Silver",1500));
            qvidDao.insert_ss(new SalonServices("Facial_Gold",3500));
            qvidDao.insert_ss(new SalonServices("Facial-Diamond",500));
            qvidDao.insert_ss(new SalonServices("Hair-Color",2000));
            qvidDao.insert_ss(new SalonServices("Manicure",1500));
            qvidDao.insert_ss(new SalonServices("Pedicure",1500));


            qvidDao.insert_s(new Salon(1000,"Naturals"));
            qvidDao.insert_s(new Salon(1001,"Green Trends"));
            qvidDao.insert_s(new Salon(1002,"Toni and Guy"));


            qvidDao.insert_day(new Day(1,"Monday"));
            qvidDao.insert_day(new Day(2,"Tuesday"));
            qvidDao.insert_day(new Day(3,"Wednesday"));
            qvidDao.insert_day(new Day(4,"Thursday"));
            qvidDao.insert_day(new Day(5,"Friday"));
            qvidDao.insert_day(new Day(6,"Saturday"));
            qvidDao.insert_day(new Day(7,"Sunday"));

            qvidDao.insert_slot(new Slot(1,"10:00 - 11:00"));
            qvidDao.insert_slot(new Slot(2,"11:00 - 12:00"));
            qvidDao.insert_slot(new Slot(3,"12:00 - 13:00"));
            qvidDao.insert_slot(new Slot(4,"13:00 - 14:00"));
            qvidDao.insert_slot(new Slot(5,"15:00 - 16:00"));
            qvidDao.insert_slot(new Slot(6,"16:00 - 17:00"));
            qvidDao.insert_slot(new Slot(7,"17:00 - 18:00"));
            qvidDao.insert_slot(new Slot(8,"18:00 - 19:00"));
            qvidDao.insert_slot(new Slot(9,"19:00 - 20:00"));

            return null;
        }
    }
}
