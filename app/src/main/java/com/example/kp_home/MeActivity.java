package com.example.kp_home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MeActivity extends AppCompatActivity {
    private Button btnSign;
    private Button btnLogOut, btnquanly;

    private ImageView btnTruyenDaLuu;
    private static final int PICK_IMAGE = 1;
    private ShapeableImageView imageProfile;
    UserBusiness userBusiness;
    private TextView txtHelloUser;
    private TextView profileUser;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        txtHelloUser = findViewById(R.id.textHelLoUser);
        userBusiness = new UserBusiness(this);

        btnSign = findViewById(R.id.SignIn);
        btnLogOut = findViewById(R.id.LogOut);
        btnTruyenDaLuu = findViewById(R.id.btndsluu);
        btnquanly = findViewById(R.id.btnql);

        if (userBusiness.isLoggedIn()) {
            User user = userBusiness.getLoggedInUser();
            if (user != null) {
                txtHelloUser.setText("Welcome, " + user.tenND);
                if ("admin".equals(user.getTenDN())) {
                    btnquanly.setVisibility(View.VISIBLE);
                } else {
                    btnquanly.setVisibility(View.GONE);
                }
            } else {
                txtHelloUser.setText("Welcome!");
                btnquanly.setVisibility(View.GONE);
            }
            btnLogOut.setVisibility(View.VISIBLE);
            btnSign.setVisibility(View.GONE);

        } else {
            txtHelloUser.setText("Welcome!");
            btnLogOut.setVisibility(View.GONE);
            btnSign.setVisibility(View.VISIBLE);
            btnquanly.setVisibility(View.GONE);
        }

        imageProfile = findViewById(R.id.imageProfile);
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userBusiness.logout();
                Intent intent = new Intent(MeActivity.this, MeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        profileUser = findViewById(R.id.ProfileUser);
        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnTruyenDaLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this, TruyenDaLuuActivity.class);
                startActivity(intent);
            }
        });

        btnquanly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this, AdminGiaoDienActivity.class);
                startActivity(intent);
            }
        });
        TextView contactUs = findViewById(R.id.ContactUs);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });


    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Failed to retrieve image", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(inputStream);
                imageProfile.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
