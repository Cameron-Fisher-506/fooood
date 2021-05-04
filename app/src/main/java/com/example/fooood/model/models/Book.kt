package com.example.fooood.model.models

import androidx.room.Entity

@Entity
class Book(var id: Int = 1, var name: String = "") : BaseModel() {
}