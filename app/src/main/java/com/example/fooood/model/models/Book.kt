package com.example.fooood.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,
    var name: String = "") : BaseModel()