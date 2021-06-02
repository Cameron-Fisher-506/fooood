package com.example.fooood.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.fooood.model.models.Category
import com.example.fooood.model.room.MealDatabase
import com.example.fooood.model.room.upsert
import com.example.fooood.model.service.FoooodService
import com.example.fooood.utils.DataAccessStrategyUtils
import com.example.fooood.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository(private val application: Application) {
    private val foooodService: FoooodService = FoooodService()
    private val categoryDao = MealDatabase.getDatabase(application).categoryDao()
    private val updateLiveData by lazy { MutableLiveData<Boolean>() }

    fun insert(categories: List<Category>) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.insert(categories)
        }
    }

    fun getAllCategories(update: Boolean): LiveData<Resource<List<Category>>>{
        updateLiveData.value = update
        return Transformations.switchMap(updateLiveData) { _ ->
            DataAccessStrategyUtils.synchronizedCache(
                application,
                { MealDatabase.getResource { categoryDao.getAll() } },
                { foooodService.getCategories() },
                { it.categories?.let { categories -> categoryDao.insert(categories) } }
            )

        }
    }
}