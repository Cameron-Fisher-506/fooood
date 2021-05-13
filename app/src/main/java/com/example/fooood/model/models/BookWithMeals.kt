package com.example.fooood.model.models

import androidx.room.Embedded
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

data class BookWithMeals(
    @Embedded
    val book: Book = Book(),

    @Relation(parentColumn = "id", entityColumn = "bookId")
    @SerializedName("meals")
    var meals: List<Meal>?) : BaseModel() {
}