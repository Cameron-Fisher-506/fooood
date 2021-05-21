package com.example.fooood.model.models

import androidx.room.Embedded
import androidx.room.Relation

class BookWithCategories(
    @Embedded
    val book: Book = Book(),

    @Relation(parentColumn = "id", entityColumn = "bookId")
    var categories: List<Category>?
) : BaseModel() {
}