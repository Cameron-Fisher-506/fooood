package com.example.fooood.model.models

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

class BookWithMeals : BaseModel() {
    @Embedded
    val book: Book = Book()

    @Relation(parentColumn = "id", entityColumn = "bookId")
    @SerializedName("meals")
    lateinit var meals: List<Meal>

}