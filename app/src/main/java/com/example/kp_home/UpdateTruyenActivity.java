// UpdateTruyenActivity.java
package com.example.kp_home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class UpdateTruyenActivity extends AppCompatActivity {

    private DBManager dbManager;
    private EditText editTenTruyen, editAnhBia, editTacGia, editNgayRaMat, editSoChuong, editTinhTrang;
    private Spinner spinnerMaTheLoai;
    private Button btnSave;
    private int maTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_truyen);

        dbManager = new DBManager(this);
        editTenTruyen = findViewById(R.id.editTenTruyen);
        editAnhBia = findViewById(R.id.editAnhBia);
        editTacGia = findViewById(R.id.editTacGia);
        editNgayRaMat = findViewById(R.id.editNgayRaMat);
        editSoChuong = findViewById(R.id.editSoChuong);
        editTinhTrang = findViewById(R.id.editTinhTrang);
        spinnerMaTheLoai = findViewById(R.id.spinnerMaTheLoai);
        btnSave = findViewById(R.id.btnSave);


        List<String> categories = dbManager.getAllCategories();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaTheLoai.setAdapter(adapter);

        maTruyen = getIntent().getIntExtra("maTruyen", -1);
        if (maTruyen != -1) {
            Truyen truyen = dbManager.getTruyenByMaTruyen(maTruyen);
            if (truyen != null) {
                editTenTruyen.setText(truyen.getTenTruyen());
                editAnhBia.setText(truyen.getAnhBia());
                editTacGia.setText(truyen.getTacGia());
                editNgayRaMat.setText(truyen.getNgayRaMat());
                editSoChuong.setText(String.valueOf(truyen.getSoChuong()));
                editTinhTrang.setText(truyen.getTinhTrang());


                int categoryPosition = adapter.getPosition(String.valueOf(truyen.getTenTheLoai()));
                spinnerMaTheLoai.setSelection(categoryPosition);
            }
        }

        btnSave.setOnClickListener(v -> {
            String tenTruyen = editTenTruyen.getText().toString();
            String anhBia = editAnhBia.getText().toString();
            String tacGia = editTacGia.getText().toString();
            String ngayRaMat = editNgayRaMat.getText().toString();
            int soChuong = Integer.parseInt(editSoChuong.getText().toString());
            String tinhTrang = editTinhTrang.getText().toString();

            String tenTheLoai = spinnerMaTheLoai.getSelectedItem().toString();
            int maTheLoai = dbManager.getMaTheLoaiByTen(tenTheLoai);


            Truyen truyen = new Truyen(maTruyen, tenTruyen, anhBia, tacGia, ngayRaMat, soChuong, tinhTrang, maTheLoai);

            boolean success = dbManager.updateTruyen(truyen);
            if (success) {
                Toast.makeText(this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
