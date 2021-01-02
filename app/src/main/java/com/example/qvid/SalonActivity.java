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


public class SalonActivity extends AppCompatActivity {
    private QvidViewModel qvidViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_view_recycler);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_salon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SalonAdapter adapter = new SalonAdapter(this);
        recyclerView.setAdapter(adapter);

        qvidViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);
        qvidViewModel.getAllSalons().observe(this, new Observer<List<Salon>>() {
            @Override
            public void onChanged(List<Salon> salons) {
                adapter.setSalons(salons);

            }
        });
    }
}
