package com.example.ivymoda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ivymoda.database.AppDatabase;
import com.example.ivymoda.database.Category;

public class AddEditCategoryActivity extends AppCompatActivity {

    private EditText edtName;
    private TextView tvTitle;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_category);

        edtName = findViewById(R.id.edtCategoryName);
        Button btnSave = findViewById(R.id.btnSave);
        tvTitle = findViewById(R.id.tvTitle);

        Intent intent = getIntent();
        if (intent.hasExtra("object_category")) {
            mCategory = (Category) intent.getSerializableExtra("object_category");
            edtName.setText(mCategory.name);
            tvTitle.setText("Sửa danh mục");
        } else {
            tvTitle.setText("Thêm danh mục mới");
        }

        btnSave.setOnClickListener(v -> saveCategory());
    }

    private void saveCategory() {
        String name = edtName.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mCategory == null) {
            Category newCat = new Category(name);
            AppDatabase.getInstance(this).categoryDao().insertCategory(newCat);
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        } else {
            mCategory.name = name;
            AppDatabase.getInstance(this).categoryDao().updateCategory(mCategory);
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}