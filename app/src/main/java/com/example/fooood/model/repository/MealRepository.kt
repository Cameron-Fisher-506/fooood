package com.example.fooood.model.repository

import android.app.Application
import com.example.fooood.model.room.IMealDao
import com.example.fooood.model.room.MealDatabase

class MealRepository(application: Application) {
    private val mealDao: IMealDao = MealDatabase.getDatabase(application).mealDao()

    
}