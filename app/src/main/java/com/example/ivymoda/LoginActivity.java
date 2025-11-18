package com.example.ivymoda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.example.ivymoda.database.AppDatabase;
import com.example.ivymoda.database.User;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtUser, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvGoToRegister = findViewById(R.id.tvGoToRegister);

        btnLogin.setOnClickListener(v -> handleLogin());

        tvGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void handleLogin() {
        String username = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = AppDatabase.getInstance(this).userDao().checkLogin(username, password);

        if (user != null) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            if (user.isAdmin) {
                startActivity(new Intent(LoginActivity.this, CategoryManagementActivity.class));
            } else {
                Toast.makeText(this, "Xin chào User: " + user.fullName, Toast.LENGTH_LONG).show();
                // Chuyển sang trang chủ user ở đây
            }
        } else {
            Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
        }
    }
}