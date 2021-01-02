package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @NonNull
    @PrimaryKey
    private String email_id;

    private String first_name,last_name,password,gender,repassword;
    private int age;

    public User(String email_id,String first_name,String last_name,String password,String gender,String repassword,int age) {
        this.email_id = email_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.gender = gender;
        this.repassword = repassword;
        this.age = age;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getRepassword() {
        return repassword;
    }

    public int getAge() {
        return age;
    }
}
