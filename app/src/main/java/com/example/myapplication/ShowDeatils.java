package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowDeatils extends AppCompatActivity {
private String email ;
private TextView tv_showemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deatils);

        email = getIntent().getStringExtra("email");
        tv_showemail = (TextView)findViewById(R.id.tv_showemail);
        tv_showemail.setText("Welcome : "+email);
    }
}
