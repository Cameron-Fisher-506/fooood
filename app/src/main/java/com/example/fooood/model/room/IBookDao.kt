package com.example.fooood.model.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.fooood.model.models.Book
import com.example.fooood.model.models.BookWithCategories

@Dao
interface IBookDao : IBaseDao<Book> {
    @Transaction
    @Query("SELECT * FROM book")
    fun getBooksWithCategories(): List<BookWithCategories>
}