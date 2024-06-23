package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private LinearLayout truyenContainer;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dbManager = new DBManager(this);
        truyenContainer = findViewById(R.id.truyenContainer);
        Button btnAddTruyen = findViewById(R.id.btnAddTruyen);

        btnAddTruyen.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddTruyenActivity.class);
            startActivityForResult(intent, 1);
        });

        Button btnTrangChu = findViewById(R.id.TrangChu);
        btnTrangChu.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        loadTruyenList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTruyenList();
    }

    private void addTruyenView(Truyen truyen) {
        View truyenView = getLayoutInflater().inflate(R.layout.truyen_item_admin, null);

        TextView tenTruyen = truyenView.findViewById(R.id.truyenName);
        TextView tacGia = truyenView.findViewById(R.id.tacGia);
        ImageView anhBia = truyenView.findViewById(R.id.imgTruyen);
        Button btnEdit = truyenView.findViewById(R.id.btnEdit);
        Button btnDelete = truyenView.findViewById(R.id.btnDelete);

        tenTruyen.setText(truyen.getTenTruyen());
        tacGia.setText(truyen.getTacGia());

        int imageResource = getResources().getIdentifier(truyen.getAnhBia(), "drawable", getPackageName());
        if (imageResource != 0) {
            Glide.with(this).load(imageResource).into(anhBia);
        }

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, UpdateTruyenActivity.class);
            intent.putExtra("maTruyen", truyen.getMaTruyen());
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminActivity.this);
            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to delete it?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                boolean success = dbManager.deleteTruyen(truyen.getMaTruyen());
                if (success) {
                    truyenContainer.removeView(truyenView);
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            androidx.appcompat.app.AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        truyenContainer.addView(truyenView);
    }

    private void loadTruyenList() {
        truyenContainer.removeAllViews();
        List<Truyen> truyenList = dbManager.getAllTruyen();
        for (Truyen truyen : truyenList) {
            addTruyenView(truyen);
        }
    }
}
