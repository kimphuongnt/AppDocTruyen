package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GetStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);

        ImageView buttonOpenSecondActivity = findViewById(R.id.start);
        buttonOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GetStartActivity.this, SignInActivity.class);
                startActivity(intent); // Bắt đầu SecondActivity
            }
        });

        TextView textViewStart = findViewById(R.id.batdau);
        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GetStartActivity.this, SignInActivity.class);
                startActivity(intent); // Bắt đầu SecondActivity
            }
        });
    }


}