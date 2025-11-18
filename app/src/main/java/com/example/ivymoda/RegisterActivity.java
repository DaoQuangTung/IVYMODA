package com.example.ivymoda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.example.ivymoda.database.AppDatabase;
import com.example.ivymoda.database.User;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText edtName, edtUser, edtPass, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtUser = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnRegister.setOnClickListener(v -> registerUser());
        tvBackToLogin.setOnClickListener(v -> finish());
    }

    private void registerUser() {
        String name = edtName.getText().toString().trim();
        String username = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        User existingUser = AppDatabase.getInstance(this).userDao().checkUsernameExist(username);
        if (existingUser != null) {
            Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Nếu username là "admin" thì cấp quyền admin, ngược lại là user thường
        boolean isAdmin = "admin".equals(username);

        User newUser = new User(username, password, name, email, isAdmin);
        AppDatabase.getInstance(this).userDao().registerUser(newUser);

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }
}