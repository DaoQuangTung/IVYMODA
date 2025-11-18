package com.example.ivymoda.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id; // maTaiKhoan

    @ColumnInfo(name = "username")
    public String username; // tenDangNhap

    @ColumnInfo(name = "password")
    public String password; // matKhau

    @ColumnInfo(name = "full_name")
    public String fullName; // hoTen

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "is_admin")
    public boolean isAdmin; // Thay thế maVaiTro: true = Admin, false = User

    // Constructor
    public User(String username, String password, String fullName, String email, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}