package com.example.ivymoda;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ivymoda.database.AppDatabase;
import com.example.ivymoda.database.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryManagementActivity extends AppCompatActivity {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> mListCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        Button btnAdd = findViewById(R.id.btnAddCategory);
        rcvCategory = findViewById(R.id.rcvCategory);

        mListCategory = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(mListCategory, new CategoryAdapter.IClickItemListener() {
            @Override
            public void onUpdateClick(Category category) {
                clickUpdateCategory(category);
            }

            @Override
            public void onDeleteClick(Category category) {
                clickDeleteCategory(category);
            }
        });

        rcvCategory.setLayoutManager(new LinearLayoutManager(this));
        rcvCategory.setAdapter(categoryAdapter);

        loadData();

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryManagementActivity.this, AddEditCategoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        mListCategory = AppDatabase.getInstance(this).categoryDao().getAllCategories();
        categoryAdapter.setData(mListCategory);
    }

    private void clickUpdateCategory(Category category) {
        Intent intent = new Intent(CategoryManagementActivity.this, AddEditCategoryActivity.class);
        intent.putExtra("object_category", category);
        startActivity(intent);
    }

    private void clickDeleteCategory(Category category) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa: " + category.name + "?")
                .setPositiveButton("Có", (dialog, which) -> {
                    // Pass the category's ID instead of the object
                    AppDatabase.getInstance(this).categoryDao().deleteCategory(category.id);
                    loadData();
                })
                .setNegativeButton("Không", null)
                .show();
    }
}