package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TruyenDaLuuActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TruyenDaLuuBusiness truyenDaLuuBusiness;
    private UserBusiness userBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_da_luu);

        linearLayout = findViewById(R.id.linearLayoutTruyenDaLuu);
        truyenDaLuuBusiness = new TruyenDaLuuBusiness(this);
        userBusiness = new UserBusiness(this);

        if (userBusiness.isLoggedIn()) {
            User user = userBusiness.getLoggedInUser();
            if (user != null) {
                int userId = userBusiness.getCurrentUserId();
                if (userId != -1) {
                    List<TruyenDaLuu> daLuu = truyenDaLuuBusiness.getTruyenDaLuu(userId);
                    displayStories(daLuu, userId);
                }
            }
        }
    }

    private void displayStories(List<TruyenDaLuu> stories, int userId) {
        linearLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (TruyenDaLuu truyen : stories) {
            View view = inflater.inflate(R.layout.truyen_item_daluu, linearLayout, false);
            TextView tvStoryTitle = view.findViewById(R.id.tenTruyen);
            ImageButton btnRemove = view.findViewById(R.id.btnRemove);

            tvStoryTitle.setText(truyen.getTenTruyen());

            view.setOnClickListener(v -> {
                Intent intent = new Intent(TruyenDaLuuActivity.this, TruyendetailActivity.class);
                intent.putExtra("maTruyen", truyen.getMaTruyen());
                startActivity(intent);
            });

            btnRemove.setOnClickListener(v -> {
                if (truyenDaLuuBusiness.deleteTruyenDaLuu(truyen)) {
                    linearLayout.removeView(view);
                    Toast.makeText(this, "Đã xóa khỏi truyện đã lưu", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            });

            linearLayout.addView(view);
        }
    }
}
