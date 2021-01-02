package com.example.qvid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qvid.QvidViewModel;
import com.example.qvid.R;
import com.example.qvid.Salon;
import com.example.qvid.SalonAdapter;
import com.example.qvid.SalonServices;
import com.example.qvid.ServiceAdapter;
import static com.example.qvid.AccountActivity.EMAIL_ID;


public class BookingHistoryActivity extends AppCompatActivity {
    private QvidViewModel qvidViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_recycler);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_booking_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BookingHistoryAdapter adapter = new BookingHistoryAdapter();
        recyclerView.setAdapter(adapter);

        qvidViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);
        qvidViewModel.getAlluserdetails(EMAIL_ID).observe(this, new Observer<List<BookingDetails>>() {
            @Override
            public void onChanged(List<BookingDetails> bookingDetails) {
                adapter.setBookingDetails_user(bookingDetails);

            }
        });
    }
}
