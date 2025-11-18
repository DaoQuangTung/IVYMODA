package com.example.ivymoda.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "taikhoan")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id; // maTaiKhoan

    @ColumnInfo(name = "tenDangNhap")
    public String username; // tenDangNhap

    @ColumnInfo(name = "matKhau")
    public String password; // matKhau

    @ColumnInfo(name = "hoTen")
    public String fullName; // hoTen

    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "soDienThoai")
    public String phoneNumber;

    @ColumnInfo(name = "ngaySinh")
    public String birthDate; // Lưu String cho đơn giản (dd/MM/yyyy)

    @ColumnInfo(name = "diaChi")
    public String address;

    @ColumnInfo(name = "gioiTinh")
    public String gender; // Lưu "Nam", "Nữ" hoặc "Khác"

    @ColumnInfo(name = "ngayTao")
    public String createdDate; // Ngày tạo tài khoản
    @ColumnInfo(name = "is_admin")
    public boolean isAdmin; // true = Admin, false = User


    // Constructor
    public User(String username, String password, String fullName, String email,
                String phoneNumber, String birthDate, String address, String gender,
                String createdDate, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.gender = gender;
        this.createdDate = createdDate;
        this.isAdmin = isAdmin;
    }
}