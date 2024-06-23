package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.View;
import android.widget.Button;

public class AdminGiaoDienActivity extends AppCompatActivity {

    private Button btnqlt, btnqldm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_giao_dien);

        btnqlt = findViewById(R.id.btnqlds);
        btnqldm = findViewById(R.id.btnqldm);


        btnqlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminGiaoDienActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        btnqldm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminGiaoDienActivity.this, AdminDanhMucActivity.class);
                startActivity(intent);
            }
        });

    }
}
