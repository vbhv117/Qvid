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


public class ServicesActivity extends AppCompatActivity {
    private QvidViewModel qvidViewModel;
    public static  String SNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_service_view_recycler);

        SNAME = getIntent().getStringExtra("sname");


        RecyclerView recyclerView = findViewById(R.id.recycler_view_services);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ServiceAdapter adapter = new ServiceAdapter(this);
        recyclerView.setAdapter(adapter);

        qvidViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);
        qvidViewModel.getAllSalonServices().observe(this, new Observer<List<SalonServices>>() {
            @Override
            public void onChanged(List<SalonServices> salonServices) {
                adapter.setSalonServices(salonServices);

            }
        });
    }
}
