package com.example.ivymoda.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
@Entity(tableName = "danhmuc")
public class Category implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id; // maDanhMuc

    @ColumnInfo(name = "tenDanhMuc")
    public String name; // tenDanhMuc

    public Category(String name) {
        this.name = name;
    }

}