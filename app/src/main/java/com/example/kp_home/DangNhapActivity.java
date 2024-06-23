package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {

    EditText etLoginUsername, etLoginPassword;
    Button btnLogin, btnRegister;
    UserBusiness userBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        userBusiness = new UserBusiness(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etLoginUsername.getText().toString();
                String password = etLoginPassword.getText().toString();

                User user = userBusiness.login(username, password);
                if (user != null) {
                    Intent intent = new Intent(DangNhapActivity.this, ProfileActivity.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("tenDN", user.getTenDN());
                    intent.putExtra("matKhau", user.getMatKhau());
                    intent.putExtra("tenND", user.getTenND());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("dob", user.getDob());
                    startActivity(intent);
                } else {
                    etLoginUsername.setError("Tên đăng nhập hoặc mật khẩu không chính xác");
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

    }
}
