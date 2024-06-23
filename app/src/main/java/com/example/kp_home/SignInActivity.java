package com.example.kp_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText SignInUserName, SignInPassword;
    private Button btnSignin;
    private TextView txtDangKy;
    UserBusiness userBusiness;
    public static boolean checkSignIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignInUserName = findViewById(R.id.signin_TenDN);
        SignInPassword = findViewById(R.id.signin_password);
        btnSignin = findViewById(R.id.signin_XacNhan);
        txtDangKy = findViewById(R.id.dangky);
        userBusiness = new UserBusiness(this);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = SignInUserName.getText().toString();
                String password = SignInPassword.getText().toString();

                User user = null;
                user = userBusiness.login(username, password);
                if (user != null) {

                    Intent intent;
                    if ("admin".equalsIgnoreCase(username)) {
                        intent = new Intent(SignInActivity.this, AdminGiaoDienActivity.class);
                        checkSignIn = true;
                        finish();
                    } else {
                        intent = new Intent(SignInActivity.this, HomeActivity.class);
                        checkSignIn = true;

                    }
                    intent.putExtra("id", user.getId());
                    intent.putExtra("tenDN", user.getTenDN());
                    intent.putExtra("matKhau", user.getMatKhau());
                    intent.putExtra("tenND", user.getTenND());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("dob", user.getDob());
                    startActivity(intent);
                } else {
                    SignInUserName.setError("Username or password not exactly");
                }
            }
        });

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
