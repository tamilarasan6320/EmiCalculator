package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class FeedbackActivity extends AppCompatActivity {

    EditText name,mobile,email,feedback;
    Button send;
    ImageButton back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        back = findViewById(R.id.toolbar);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        feedback = findViewById(R.id.feedback);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equals("")){
                    name.setError("empty");
                    name.requestFocus();
                }
                else if (mobile.getText().toString().trim().equals("")){
                    mobile.setError("empty");
                    mobile.requestFocus();
                }
                else if (email.getText().toString().trim().equals("")){
                    email.setError("empty");
                    email.requestFocus();
                }
                else if (feedback.getText().toString().trim().equals("")){
                    feedback.setError("empty");
                    feedback.requestFocus();
                }
                else{
                    String stremail = "jayaprasad356@gmail.com";
                    String subject = "Feedback From "+name.getText().toString().trim();
                    String message = "Name : "+name.getText().toString().trim() + "\n"+"Email : "+email.getText().toString().trim()+ "\n" + "Mobile : "+mobile.getText().toString().trim()+ "\n" + "Feedback : "+feedback.getText().toString().trim();
                    SendMail sm = new SendMail(FeedbackActivity.this, stremail, subject, message);
                    sm.execute();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


    }



}