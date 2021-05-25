package com.example.fooood.model.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(indices = [Index(value = ["id"], unique = true)])
class Category: BaseModel(), Serializable {
    var bookId: Int = 1
    @SerializedName("idCategory")
    @PrimaryKey(autoGenerate = false)
    var id: String = ""
    @SerializedName("strCategory")
    var category: String = ""
    @SerializedName("strCategoryThumb")
    var categoryThumb: String = ""
    @SerializedName("strCategoryDescription")
    var categoryDescription: String = ""
}