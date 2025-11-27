package com.example.ivymoda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
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

        // 1. Ánh xạ các View
        Button btnAdd = findViewById(R.id.btnAddCategory);
        ImageView btnLogout = findViewById(R.id.btnLogout); // Nút đăng xuất
        rcvCategory = findViewById(R.id.rcvCategory);

        // 2. Cấu hình RecyclerView và Adapter
        mListCategory = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(mListCategory, new CategoryAdapter.IClickItemListener() {
            @Override
            public void onUpdateClick(Category category) {
                // Sự kiện khi bấm nút Sửa (Bút chì)
                clickUpdateCategory(category);
            }

            @Override
            public void onDeleteClick(Category category) {
                // Sự kiện khi bấm nút Xóa (Thùng rác)
                clickDeleteCategory(category);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCategory.setLayoutManager(linearLayoutManager);
        rcvCategory.setAdapter(categoryAdapter);

        // Tải dữ liệu lần đầu
        loadData();

        // 3. Sự kiện bấm nút Thêm mới
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryManagementActivity.this, AddEditCategoryActivity.class);
                startActivity(intent);
            }
        });

        // 4. Sự kiện bấm nút Đăng xuất
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }

    // Hàm load lại dữ liệu khi quay lại màn hình này (Sau khi thêm/sửa xong)
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    // Hàm lấy dữ liệu từ Database và đưa vào Adapter
    private void loadData() {
        mListCategory = AppDatabase.getInstance(this).categoryDao().getAllCategories();
        categoryAdapter.setData(mListCategory);
    }

    // Hàm xử lý chuyển sang màn hình Sửa
    private void clickUpdateCategory(Category category) {
        Intent intent = new Intent(CategoryManagementActivity.this, AddEditCategoryActivity.class);
        intent.putExtra("object_category", category); // Gửi đối tượng category sang để hiển thị lại
        startActivity(intent);
    }

    // Hàm xử lý Xóa
    private void clickDeleteCategory(Category category) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa danh mục: " + category.name + "?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Gọi DAO để xóa trong Database
                        AppDatabase.getInstance(CategoryManagementActivity.this).categoryDao().deleteCategory(category.id);
                        // Thông báo và load lại danh sách
                        Toast.makeText(CategoryManagementActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    // Hàm hiển thị hộp thoại xác nhận Đăng xuất
    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                .setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        performLogout();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    // Hàm thực hiện Đăng xuất và xóa lịch sử Back stack
    private void performLogout() {
        Intent intent = new Intent(CategoryManagementActivity.this, LoginActivity.class);
        // Cờ này giúp xóa sạch các Activity cũ, người dùng không thể bấm Back để quay lại đây
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}