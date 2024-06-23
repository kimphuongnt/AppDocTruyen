package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class NoiDungTruyenActivity extends AppCompatActivity {

    private TextView tenChuongTextView;
    private TextView noiDungTextView;
    private ImageButton btnPreviousChapter, btnNextChapter;
    private ImageButton btnXemChuong;
    private int currentChapterIndex;
    private List<ChiTietTruyen> chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_truyen);

        tenChuongTextView = findViewById(R.id.tenChuongTextView);
        noiDungTextView = findViewById(R.id.noiDungTextView);
        btnPreviousChapter = findViewById(R.id.btnPreviousChapter);
        btnNextChapter = findViewById(R.id.btnNextChapter);

        btnXemChuong = findViewById(R.id.btnXemChuong);

        int maTruyen = getIntent().getIntExtra("maTruyen", -1);
        currentChapterIndex = getIntent().getIntExtra("currentChapterIndex", 0);

        ChiTietTruyenBusiness chiTietTruyenBusiness = new ChiTietTruyenBusiness(this);
        chapterList = chiTietTruyenBusiness.getDanhSachChuong(maTruyen);

        if (chapterList != null && !chapterList.isEmpty()) {
            displayCurrentChapter(maTruyen, currentChapterIndex);

            btnPreviousChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentChapterIndex > 0) {
                        currentChapterIndex--;
                        displayCurrentChapter(maTruyen, currentChapterIndex);
                    }
                }
            });

            btnNextChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentChapterIndex < chapterList.size() - 1) {
                        currentChapterIndex++;
                        displayCurrentChapter(maTruyen, currentChapterIndex);
                    }
                }
            });

        } else {
            tenChuongTextView.setText("Không có truyện");
            noiDungTextView.setText("");
            btnPreviousChapter.setVisibility(View.GONE);
            btnNextChapter.setVisibility(View.GONE);
        }
        btnXemChuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoiDungTruyenActivity.this, ChuongDetailActivity.class);
                intent.putExtra("maTruyen", maTruyen);
                startActivity(intent);
            }
        });
    }

    private void displayCurrentChapter(int maTruyen, int chapterIndex) {
        ChiTietTruyen currentChapter = chapterList.get(chapterIndex);

        if (currentChapter != null) {
            tenChuongTextView.setText(currentChapter.getTenChuong());
            noiDungTextView.setText(currentChapter.getNoiDung());


            btnPreviousChapter.setVisibility(currentChapterIndex > 0 ? View.VISIBLE : View.GONE);
            btnNextChapter.setVisibility(currentChapterIndex < chapterList.size() - 1 ? View.VISIBLE : View.GONE);
        }
    }
}
