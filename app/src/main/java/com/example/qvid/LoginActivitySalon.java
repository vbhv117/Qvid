package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivitySalon extends AppCompatActivity {

    private EditText userText_salon,passText_salon;
    private ImageView logoimg;
    private Button login_but_salon;
    private QvidViewModel qvidViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_salon);

        logoimg = findViewById(R.id.logo);
        userText_salon = findViewById(R.id.salon_name_login);
        passText_salon = findViewById(R.id.salon_id_salon);
        login_but_salon = findViewById(R.id.loginBut_salon);


        qvidViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);

        login_but_salon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String salon_name,salon_id;
                salon_name = userText_salon.getText().toString().trim();
                salon_id = passText_salon.getText().toString().trim();
                qvidViewModel.getSalonid(salon_name).observe(LoginActivitySalon.this, new Observer<List<Salon>>() {
                    @Override
                    public void onChanged(List<Salon> salons) {
                        if (salons.size()==0){
                            Toast.makeText(LoginActivitySalon.this,"Please check your details again!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                                Intent intent = new Intent(LoginActivitySalon.this,AdminActivity.class);
                                intent.putExtra("Salon_id", salons.get(0).getSalonid());
                                intent.putExtra("Salon_name",salons.get(0).getSalonname());
                                startActivity(intent);
                            }
                        }

                });

            }
        });
    }
}