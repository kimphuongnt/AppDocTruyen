package com.example.kp_home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText etUsername, etFullName, etEmail, etDob, etGender;
    private Button btnEdit, btnSave, btnLogin, btnLogout;
    private UserBusiness userBusiness;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etUsername = findViewById(R.id.etUsername);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etDob = findViewById(R.id.etDob);
        etGender = findViewById(R.id.etGender);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogout = findViewById(R.id.btnLogout);

        userBusiness = new UserBusiness(this);

        if (userBusiness.isLoggedIn()) {
            currentUser = userBusiness.getLoggedInUser();
            displayUserInfo(currentUser);
            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);
        }

        btnEdit.setOnClickListener(v -> enableEditing(true));
        btnSave.setOnClickListener(v -> confirmSaveChanges());
        btnLogout.setOnClickListener(v -> userBusiness.logout());
    }

    private void displayUserInfo(User user) {
        etUsername.setText(user.getTenDN());
        etFullName.setText(user.getTenND());
        etEmail.setText(user.getEmail());
        etDob.setText(user.getDob());
    }

    private void enableEditing(boolean enabled) {
        etUsername.setEnabled(enabled);
        etFullName.setEnabled(enabled);
        etEmail.setEnabled(enabled);
        etDob.setEnabled(enabled);
        etGender.setEnabled(enabled);
        btnSave.setVisibility(enabled ? View.VISIBLE : View.GONE);
        btnEdit.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void confirmSaveChanges() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Save")
                .setMessage("Bạn có chắc chắn lưu?")
                .setPositiveButton("Yes", (dialog, which) -> saveChanges())
                .setNegativeButton("No", null)
                .show();
    }

    private void saveChanges() {
        String username = etUsername.getText().toString();
        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String dob = etDob.getText().toString();
        String gender = etGender.getText().toString();

        currentUser.setTenDN(username);
        currentUser.setTenND(fullName);
        currentUser.setEmail(email);
        currentUser.setDob(dob);

        if (userBusiness.updateUser(currentUser)) {
            Toast.makeText(this, "Chỉnh sửa thông tin thành công", Toast.LENGTH_SHORT).show();
            enableEditing(false);
        } else {
            Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
        }
    }
}
