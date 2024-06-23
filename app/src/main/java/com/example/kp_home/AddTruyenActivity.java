package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddTruyenActivity extends AppCompatActivity {

    private EditText etStoryName;
    private EditText etAuthor;
    private EditText etCoverImage;
    private Spinner spinnerCategory;
    private Button btnSaveStory;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truyen);

        dbManager = new DBManager(this);
        etStoryName = findViewById(R.id.addTenTruyen);
        etAuthor = findViewById(R.id.addTacGia);
        etCoverImage = findViewById(R.id.addAnhBia);
        spinnerCategory = findViewById(R.id.spinnerMaTheLoai);
        btnSaveStory = findViewById(R.id.btnSave);

        List<String> categories = dbManager.getAllCategories();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        btnSaveStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStory();
            }
        });
    }

    private void saveStory() {
        String storyName = etStoryName.getText().toString();
        String author = etAuthor.getText().toString();
        String coverImage = etCoverImage.getText().toString();
        String selectedCategory = spinnerCategory.getSelectedItem().toString();

        int categoryId = dbManager.getMaTheLoaiByTen(selectedCategory);

        Truyen newStory = new Truyen();
        newStory.setTenTruyen(storyName);
        newStory.setTacGia(author);
        newStory.setAnhBia(coverImage);
        newStory.setMaTheLoai(categoryId);

        boolean result = dbManager.addTruyen(newStory);
        if (result) {
            Toast.makeText(AddTruyenActivity.this, "Inserted", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(AddTruyenActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
