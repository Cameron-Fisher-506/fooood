package com.example.fooood.model.models

import androidx.room.Entity

@Entity
class Book(id: Int, name: String): BaseModel() {
    var id: Int = 1
    var name: String = ""

    init {
        this.id = id
        this.name = name
    }
}