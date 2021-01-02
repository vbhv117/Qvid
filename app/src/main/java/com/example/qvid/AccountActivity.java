package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
    private TextView hi_user;
    private Button booking_history_btn, map_activity, book_appointment;
    public static String USER_NAME;
    public static String EMAIL_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        hi_user =findViewById(R.id.hi_user);
        map_activity = findViewById(R.id.map_activity);
        book_appointment = findViewById(R.id.book_appointment);
        USER_NAME = getIntent().getStringExtra("Name");
        EMAIL_ID = getIntent().getStringExtra("Email");
        String name = "Hello " + getIntent().getStringExtra("Name");
        hi_user.setText(name);
        booking_history_btn = findViewById(R.id.booking_history);
//        hi_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent (AccountActivity.this,SalonActivity.class);
//                startActivity(intent);
//
//            }
//        });

        book_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (AccountActivity.this,SalonActivity.class);
                startActivity(intent);

            }
        });
        booking_history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,BookingHistoryActivity.class);
                startActivity(intent);

            }
        });

        map_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, NearbySalons.class);
                startActivity(intent);
            }
        });
    }
}