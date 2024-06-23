package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ChuongDetailActivity extends AppCompatActivity {

    private LinearLayout danhsachchuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuong_detail);

        danhsachchuong = findViewById(R.id.danhsachchuong);

        int maTruyen = getIntent().getIntExtra("maTruyen", -1);
        if (maTruyen != -1) {
            ChiTietTruyenBusiness chiTietTruyenBusiness = new ChiTietTruyenBusiness(this);
            List<ChiTietTruyen> chapters = chiTietTruyenBusiness.getDanhSachChuong(maTruyen);
            for (int i = 0; i < chapters.size(); i++) {
                ChiTietTruyen chapter = chapters.get(i);
                TextView textView = new TextView(this);
                textView.setText(chapter.getTenChuong());
                textView.setTextSize(20);
                textView.setPadding(0, 8, 0, 8);
                final int chapterIndex = i;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ChuongDetailActivity.this, NoiDungTruyenActivity.class);
                        intent.putExtra("maTruyen", maTruyen);
                        intent.putExtra("currentChapterIndex", chapterIndex);
                        startActivity(intent);
                    }
                });
                danhsachchuong.addView(textView);
            }
        }
    }
}
