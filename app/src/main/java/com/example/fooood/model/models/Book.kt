package com.example.fooood.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,
    var name: String = "") : BaseModel()