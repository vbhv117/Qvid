package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    private Button booking_salon,manage_bookings;
    private TextView hi_admin;
    private String salon_name_admin;
    private int salon_id_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        manage_bookings = findViewById(R.id.manage_bookings);
        booking_salon = findViewById(R.id.bookings_admin);
        hi_admin =findViewById(R.id.hi_admin);
        salon_name_admin = getIntent().getStringExtra("Salon_name");
        salon_id_admin = getIntent().getIntExtra("Salon_id",0);
        String name = ("Welcome to " + salon_name_admin +" Admin page");
        hi_admin.setText(name);

        booking_salon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminBookingHistoryActivity.class );
                intent.putExtra("id",salon_id_admin);
                startActivity(intent);


            }
        });

        manage_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ManageBookingsActivity.class);
                intent.putExtra("id",salon_id_admin);
                startActivity(intent);
            }
        });


    }
}