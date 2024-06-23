package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DangKyActivity extends AppCompatActivity {

    EditText etRegisterUsername, etRegisterPassword, etRegisterFullName, etRegisterEmail, etRegisterDob, etRegisterGender;
    Button btnRegister;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etRegisterFullName = findViewById(R.id.etRegisterFullName);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterDob = findViewById(R.id.etRegisterDob);
        etRegisterGender = findViewById(R.id.etRegisterGender);
        btnRegister = findViewById(R.id.btnRegister);

        dbManager = new DBManager(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etRegisterUsername.getText().toString();
                String password = etRegisterPassword.getText().toString();
                String fullName = etRegisterFullName.getText().toString();
                String email = etRegisterEmail.getText().toString();
                String dob = etRegisterDob.getText().toString();
                String gender = etRegisterGender.getText().toString();

                User newUser = new User(0, username, password, fullName, email, dob);

                boolean success = dbManager.addUser(newUser);
                if (success) {
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DangKyActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
