package com.example.ivymoda.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void registerUser(User user);

    @Query("SELECT * FROM taikhoan WHERE tenDangNhap = :uName AND matKhau = :pass LIMIT 1")
    User checkLogin(String uName, String pass);

    @Query("SELECT * FROM taikhoan WHERE tenDangNhap = :uName")
    User checkUsernameExist(String uName);
    @Query("SELECT * FROM taikhoan WHERE email = :mail")
    User checkEmailExist(String mail);
}