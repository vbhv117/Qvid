package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.qvid.SalonAdapter.SALON_ID;
import static com.example.qvid.AccountActivity.EMAIL_ID;
import static com.example.qvid.SalonAdapter.SALON_NAME;
import static com.example.qvid.AccountActivity.USER_NAME;

public class AppointmentActivity extends AppCompatActivity {
    private QvidViewModel qvidViewModel;
    private QvidRepository qvidRepository;
    private Spinner spinner_day;
    private Spinner spinner_slot;
    private static final String TAG = "AppointmentActivity";
    private Button btn_summary;
    private String SERVICE_NAME;
    private  int SERVICE_RATE;
    private String selected_slot;
    private String selected_day;
    private int d_id;
    private int slot_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        spinner_day = (findViewById(R.id.spinner_day));
        spinner_slot = (findViewById(R.id.spinner_slot));
        btn_summary = findViewById(R.id.btn_summary);
        SERVICE_NAME = getIntent().getStringExtra("sername");
        SERVICE_RATE = getIntent().getIntExtra("srate",0);


        final ArrayList<String> days = new ArrayList<>();
        days.add("Select a day");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        ArrayAdapter<String> daysadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,days);
        spinner_day.setAdapter(daysadapter);

        qvidViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);
        qvidRepository = new QvidRepository(getApplication());

        spinner_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_day = adapterView.getItemAtPosition(i).toString();
                qvidViewModel.getDayid(selected_day).observe(AppointmentActivity.this, new Observer<List<Integer>>() {
                    @Override
                    public void onChanged(List<Integer> integers_day) {

                        if (integers_day.size() != 0) {
                            final ArrayList<Integer> slots = new ArrayList<>();
                            slots.add(1);
                            slots.add(2);
                            slots.add(3);
                            slots.add(4);
                            slots.add(5);
                            slots.add(6);
                            slots.add(7);
                            slots.add(8);
                            slots.add(9);
                            final ArrayList<String> avail_slots = new ArrayList<>();
                            d_id = integers_day.get(0);

                            qvidViewModel.getSlotid(SALON_ID, d_id).observe(AppointmentActivity.this, new Observer<List<Integer>>() {
                                @Override
                                public void onChanged(List<Integer> integers_slot) {
                                    Log.d(TAG, "onChanged: integers_slot"+ integers_slot.size());
                                    Log.d(TAG, "onChanged: slots_size"+slots.size());
                                    for (Integer i : integers_slot) {
                                        Log.d(TAG, "onChanged: inside_for_loop");
                                        slots.remove(i);
                                        Log.d(TAG, "onChanged: slots_size"+slots.size());

                                    }
                                    avail_slots.add("Select a Slot");

                                    Log.d(TAG, "onChanged: slots_size"+slots.size());
                                    AsyncTask.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            for (Integer i : slots) {
                                                Log.d(TAG, "onChanged: i "+i);
                                                List<String> temp = qvidRepository.getSlotname(i);
                                                Log.d(TAG, "onChanged: strings_dayname"+ temp.get(0));
                                                avail_slots.add(temp.get(0));
                                                Log.d(TAG, "onChanged: avail_slot"+ avail_slots.size());

                                            }

                                        }
                                    });

                                    Log.d(TAG, "onChanged: avail_slot_final"+avail_slots.size());
                                    ArrayAdapter<String> slotadapter = new ArrayAdapter<>(AppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, avail_slots);
                                    spinner_slot.setAdapter(slotadapter);
                                    spinner_slot.setVisibility(View.VISIBLE);

                                }
                            });
                        }

//                        else
//                            Toast.makeText(AppointmentActivity.this, "You have failed successfully", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_slot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selected_slot = adapterView.getItemAtPosition(i).toString();
                qvidViewModel.get_Slot_id(selected_slot).observe(AppointmentActivity.this, new Observer<List<Integer>>() {
                    @Override
                    public void onChanged(List<Integer> integers) {
                        if(integers.size()>0){
                            slot_id = integers.get(0);
                        }

                    }
                });
;            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(AppointmentActivity.this).create();
                alertDialog.setTitle("Booking Confirmation ");
                alertDialog.setMessage("Salon: "+SALON_NAME+"\n"+
                        "Service: "+SERVICE_NAME+ "\n"+
                        "Rate: "+SERVICE_RATE+ "\n"+
                        "Day: " + selected_day+ "\n"+
                        "Slot: "+ selected_slot);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        qvidViewModel.insert_bd(new BookingDetails(EMAIL_ID,SALON_ID,SALON_NAME,SERVICE_NAME,SERVICE_RATE,selected_day,selected_slot,USER_NAME,"Booked"));
                        qvidViewModel.insert_intermediate(new Intermediate(SALON_ID,d_id,slot_id));
                        Toast.makeText(AppointmentActivity.this,"Booking Successful",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "RETURN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });




//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
                alertDialog.setCancelable(false);
               alertDialog.show();

            }
        });

    }

}