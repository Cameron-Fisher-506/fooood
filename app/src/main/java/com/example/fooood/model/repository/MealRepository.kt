package com.example.fooood.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.fooood.model.models.Meal
import com.example.fooood.model.room.IMealDao
import com.example.fooood.model.room.MealDatabase
import com.example.fooood.model.service.FoooodService
import com.example.fooood.utils.DataAccessStrategyUtils
import com.example.fooood.utils.Resource

class MealRepository(application: Application) {
    private val foooodService: FoooodService = FoooodService()
    private val mealDao: IMealDao = MealDatabase.getDatabase(application).mealDao()

    private val categoryLiveData by lazy { MutableLiveData<String>() }
    private val idLiveData by lazy { MutableLiveData<String>() }
    private val nameLiveData by lazy { MutableLiveData<String>() }

    fun getMealsByCategory(category: String): LiveData<Resource<List<Meal>>> {
        categoryLiveData.value = category
        return Transformations.switchMap(categoryLiveData) {DataAccessStrategyUtils.synchronizedCache(mealDao, null) {foooodService.getMealsByCategory(it)} }
    }

    fun getMealById(id: String): LiveData<Resource<List<Meal>>> {
        idLiveData.value = id
        return Transformations.switchMap(idLiveData) { DataAccessStrategyUtils.synchronizedCache(mealDao, it) {foooodService.getMealById(it)} }
    }

    fun getMealsByName(name: String): LiveData<Resource<List<Meal>>> {
        nameLiveData.value = name
        return Transformations.switchMap(nameLiveData) { DataAccessStrategyUtils.synchronizedCache(mealDao, null) {foooodService.getMealsByName(it)} }
    }
}