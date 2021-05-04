package com.example.fooood.model.room

import androidx.room.Dao
import com.example.fooood.model.models.Book

@Dao
interface IBookDao : IBaseDao<Book> {
}