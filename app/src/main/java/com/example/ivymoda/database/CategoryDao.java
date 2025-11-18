package com.example.ivymoda.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insertCategory(Category category);
    @Update
    void updateCategory(Category category);

    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();

    // Bạn có thể thêm các hàm khác sau này như delete, update...
    @Query("DELETE FROM categories WHERE id = :id")
    void deleteCategory(int id);
}