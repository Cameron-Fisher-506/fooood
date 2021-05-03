package com.example.fooood.model.repository

import android.app.Application
import com.example.fooood.model.models.Book
import com.example.fooood.model.room.IBookDao
import com.example.fooood.model.room.MealDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookRepository(application: Application) {
    private val bookDao: IBookDao = MealDatabase.getDatabase(application).bookDao()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.insert(Book(1, "Recipes"))
        }
    }
}