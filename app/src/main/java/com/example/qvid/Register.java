package com.example.qvid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Register extends AppCompatActivity {

    private EditText editText1,editText2,editText3,editText4,editText5,age;
    private QvidViewModel qvidViewModel;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RelativeLayout layout;
    private boolean notExists = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText1 = findViewById(R.id.FirstName);
        editText2 = findViewById(R.id.LastName);
        editText3 = findViewById(R.id.EamilId);
        editText4 = findViewById(R.id.Password);
        editText5 = findViewById(R.id.RePassword);
        button = findViewById(R.id.Register);
        age = findViewById(R.id.Age);
        layout = findViewById(R.id.parent);

        radioGroup = findViewById(R.id.RgGroup);


        qvidViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QvidViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fname, lname, email, password, repassword, gender;
                final int age1;
                notExists = false;

                fname = editText1.getText().toString().trim();
                lname = editText2.getText().toString().trim();
                email = editText3.getText().toString().trim();
                password = editText4.getText().toString().trim();
                repassword = editText5.getText().toString().trim();
                age1 = Integer.parseInt(age.getText().toString());
                int selectid = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectid);
                gender = radioButton.getText().toString().trim();
                qvidViewModel.getLogin(email).observe(Register.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        for (User u : users) {
                            if (u.getEmail_id().equals(email)) {
                                notExists = true;
                                break;
                            }
                        }
                    }
                });


                if (notExists) {
                    Toast.makeText(Register.this, "Account already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (fname.equals("") || lname.equals("") || email.equals("") || password.equals("") || repassword.equals("")) {
                        Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.equals(repassword)) {
                            qvidViewModel.insert_user(new User(email, fname, lname, password, gender, repassword, age1));
                            editText1.getText().clear();
                            editText2.getText().clear();
                            editText3.getText().clear();
                            editText4.getText().clear();
                            editText5.getText().clear();
                            age.getText().clear();
                            Intent intent = new Intent(Register.this,AccountActivity.class);
                            intent.putExtra("Email",email);
                            intent.putExtra("Name",fname);
                            startActivity(intent);
                            showSnackbar();

                        } else {
                            showSnackbar1();
                        }
                    }
                }
            }
        });
    }

    private void showSnackbar() {
        Snackbar.make(layout, "Registered Successfully", Snackbar.LENGTH_INDEFINITE)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Register.this, "Close Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .setTextColor(Color.RED)
                .show();

    }

    private void showSnackbar1() {
        Snackbar.make(layout, "WARNING Fill all the fields", Snackbar.LENGTH_INDEFINITE)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Register.this, "Close Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .setTextColor(Color.RED)
                .show();


    }
}
