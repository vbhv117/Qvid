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

public class Login_Activity extends AppCompatActivity {

    private EditText userText,passText;
    private ImageView logoimg;
    private Button login_but;
    private QvidViewModel qvidViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        logoimg = findViewById(R.id.logo);
        userText = findViewById(R.id.userid);
        passText = findViewById(R.id.passwordid);
        login_but = findViewById(R.id.loginBut);


        qvidViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);

        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail,password;
                mail = userText.getText().toString().trim();
                password = passText.getText().toString().trim();
                qvidViewModel.getLogin(mail).observe(Login_Activity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        if (users.size()==0){
                            Toast.makeText(Login_Activity.this,"Please check your email again!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(password.equals(users.get(0).getRepassword())){
                                Intent intent = new Intent(Login_Activity.this,AccountActivity.class);
                                intent.putExtra("Name", users.get(0).getFirst_name());
                                intent.putExtra("Email",users.get(0).getEmail_id());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Login_Activity.this,"Please check your Password again!",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            }
        });
    }
}