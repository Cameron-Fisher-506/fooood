package com.example.fooood.model.room

import androidx.room.Dao
import androidx.room.Query
import com.example.fooood.model.models.Category

@Dao
interface ICategoryDao: IBaseDao<Category> {
    @Query("SELECT * FROM category")
    fun getAll(): List<Category>
}