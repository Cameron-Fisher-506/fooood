package com.example.fooood.model.room

import androidx.room.Dao
import androidx.room.Query
import com.example.fooood.model.models.Meal

@Dao
interface IMealDao : IBaseDao<Meal> {

    @Query("SELECT * FROM meal WHERE meal LIKE ('%'|:value|'%')")
    suspend fun getAllByValue(value: String): List<Meal>?

    @Query("SELECT * FROM meal ORDER BY RANDOM() LIMIT 100")
    suspend fun getRandomMeals(): List<Meal>?
}