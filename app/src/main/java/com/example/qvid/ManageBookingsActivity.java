package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.AsynchronousByteChannel;
import java.util.List;

public class ManageBookingsActivity extends AppCompatActivity {
    private EditText managebookingset;
    private Button managebookingsbtn;
    private int salon_id;
    private  QvidViewModel qvidViewModel;
    private  QvidRepository qvidRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bookings);

        managebookingsbtn = findViewById(R.id.button_manage_bookings);
        managebookingset = findViewById(R.id.textview_manage_bookings);

        salon_id = getIntent().getIntExtra("id",0);

        qvidViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);
        qvidRepository = new QvidRepository(getApplication());

        managebookingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String day;
                        int id=0;


                        day = managebookingset.getText().toString().trim();

                        if(day.equals("Monday"))
                            id = 1;
                        else if(day.equals("Tuesday"))
                            id = 2;
                        else if(day.equals("Wednesday"))
                            id = 3;
                        else if(day.equals("Thursday"))
                            id = 4;
                        else if(day.equals("Friday"))
                            id = 5;
                        else if(day.equals("Saturday"))
                            id = 6;
                        else if(day.equals("Sunday"))
                            id = 7;

                        List<BookingDetails> bookingDetails = qvidRepository.get_update_details(salon_id,day);

                        for(BookingDetails bs:bookingDetails){
                            bs.setStatus("Finished");
                            qvidViewModel.update_bd(bs);
                        }

                        List<Intermediate> intermediates = qvidRepository.getIntermediate(salon_id,id);
                        if(intermediates.size()>0){
                            for (Intermediate i: intermediates)
                            {
                                qvidViewModel.delete_intermediate(i);
                            }
                            ManageBookingsActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ManageBookingsActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                                }
                            });
                            finish();


                        }
                        else
                            {
                            ManageBookingsActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ManageBookingsActivity.this, "You have failed successfully", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }


                    }
                });

            }
        });





    }
}
