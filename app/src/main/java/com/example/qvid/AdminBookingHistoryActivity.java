package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

//import static com.example.qvid.AdminActivity.SALON_NAME_ADMIN;

public class AdminBookingHistoryActivity extends AppCompatActivity {
    private QvidViewModel qvidViewModel;
    private int salon_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking_history);

        salon_id = getIntent().getIntExtra("id",0);

        RecyclerView recyclerView = findViewById(R.id.booking_history_admin_card_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AdminBookingHistoryAdapter adapter = new AdminBookingHistoryAdapter();
        recyclerView.setAdapter(adapter);

        qvidViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);
        qvidViewModel.getAlladmindetails(salon_id).observe(this, new Observer<List<BookingDetails>>() {
            @Override
            public void onChanged(List<BookingDetails> bookingDetails) {
                adapter.setBookingDetails_admin(bookingDetails);

            }
        });
    }
}