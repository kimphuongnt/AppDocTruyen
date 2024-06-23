package com.example.kp_home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminDanhMucActivity extends AppCompatActivity {

    private LinearLayout linearLayoutCategories;
    private Button buttonAddCategory;
    private DBManager dbManager;
    private List<TheLoai> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_danh_muc);

        linearLayoutCategories = findViewById(R.id.linearLayoutCategories);
        buttonAddCategory = findViewById(R.id.buttonAddCategory);

        dbManager = new DBManager(this);

        buttonAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCategoryDialog();
            }
        });

        loadCategories();
    }

    private void loadCategories() {
        linearLayoutCategories.removeAllViews();
        categoryList = dbManager.getAllCategoriesAdmin();

        for (final TheLoai category : categoryList) {
            View categoryView = LayoutInflater.from(this).inflate(R.layout.item_category, linearLayoutCategories, false);

            TextView textViewCategoryName = categoryView.findViewById(R.id.textViewCategoryName);
            Button buttonEditCategory = categoryView.findViewById(R.id.buttonEditCategory);
            Button buttonDeleteCategory = categoryView.findViewById(R.id.buttonDeleteCategory);

            textViewCategoryName.setText(category.getTenTheLoai());

            buttonEditCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showEditCategoryDialog(category);
                }
            });

            buttonDeleteCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCategory(category.getMaTheLoai());
                }
            });

            linearLayoutCategories.addView(categoryView);
        }
    }

    private void showAddCategoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Category");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String categoryName = input.getText().toString();
                if (!categoryName.isEmpty()) {
                    TheLoai newCategory = new TheLoai();
                    newCategory.setTenTheLoai(categoryName);
                    if (dbManager.addTheLoai(newCategory)) {
                        loadCategories();
                        Toast.makeText(AdminDanhMucActivity.this, "Category added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminDanhMucActivity.this, "Failed to add category", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminDanhMucActivity.this, "Category name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void showEditCategoryDialog(final TheLoai theLoai) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Category");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(theLoai.getTenTheLoai());
        builder.setView(input);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String categoryName = input.getText().toString();
                if (!categoryName.isEmpty()) {
                    theLoai.setTenTheLoai(categoryName);
                    if (dbManager.updateTheLoai(theLoai)) {
                        loadCategories();
                        Toast.makeText(AdminDanhMucActivity.this, "Category updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminDanhMucActivity.this, "Failed to update category", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminDanhMucActivity.this, "Category name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteCategory(final String maTheLoaiString) {
        // Convert maTheLoaiString to int
        final int maTheLoai = Integer.parseInt(maTheLoaiString);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Category");
        builder.setMessage("Are you sure you want to delete this category?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dbManager.deleteTheLoai(maTheLoai)) {
                    loadCategories();
                    Toast.makeText(AdminDanhMucActivity.this, "Category deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminDanhMucActivity.this, "Failed to delete category", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
