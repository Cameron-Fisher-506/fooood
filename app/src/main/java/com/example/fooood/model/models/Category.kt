package com.example.fooood.model.models

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["id"], unique = true)])
class Category: BaseModel() {
    var bookId: Int = 1
    @SerializedName("idCategory")
    var id: String = ""
    @SerializedName("strCategory")
    var category: String = ""
    @SerializedName("strCategoryThumb")
    var categoryThumb: String = ""
    @SerializedName("strCategoryDescription")
    var categoryDescription: String = ""
}