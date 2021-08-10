package com.example.fooood.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.fooood.model.models.Meal
import com.example.fooood.model.room.IMealDao
import com.example.fooood.model.room.MealDatabase
import com.example.fooood.model.room.upsert
import com.example.fooood.model.service.FoooodService
import com.example.fooood.utils.DataAccessStrategyUtils
import com.example.fooood.utils.Resource

class MealRepository(private val application: Application) {
    private val foooodService: FoooodService = FoooodService()
    private val mealDao: IMealDao = MealDatabase.getDatabase(application).mealDao()
    
    suspend fun fetchMealsByCategory(category: String): Resource<List<Meal>> {
        return DataAccessStrategyUtils.synchronizedCache(
            application,
            { MealDatabase.getResource { mealDao.getByCategory(category) } },
            { foooodService.getMealsByCategory(category) },
            {  }
        )
    }

    suspend fun fetchMealById(id: String): Resource<List<Meal>> {
        return DataAccessStrategyUtils.synchronizedCache(
            application,
            { MealDatabase.getResource { mealDao.getById(id) } } ,
            { foooodService.getMealById(id) },
            { it.meals?.let { meals -> mealDao.upsert(meals, mealDao) } }
        )
    }

    suspend fun fetchMealsByName(name: String): Resource<List<Meal>> {
        return DataAccessStrategyUtils.synchronizedCache(
            application,
            { MealDatabase.getResource { mealDao.getAllByName(name) } },
            { foooodService.getMealsByName(name) },
            { it.meals?.let { meals -> mealDao.upsert(meals, mealDao) } }
        )
    }
}