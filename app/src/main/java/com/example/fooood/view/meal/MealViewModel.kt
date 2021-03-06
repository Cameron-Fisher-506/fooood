package com.example.fooood.view.meal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.fooood.model.models.Category
import com.example.fooood.model.models.Meal
import com.example.fooood.model.repository.BookRepository
import com.example.fooood.model.repository.CategoryRepository
import com.example.fooood.model.repository.MealRepository
import com.example.fooood.utils.Resource

class MealViewModel(application: Application): AndroidViewModel(application) {
    lateinit var randomMealsLiveData: LiveData<Resource<List<Meal>>>
    lateinit var mealLiveData: LiveData<Resource<List<Meal>>>
    lateinit var categoriesLiveDate: LiveData<Resource<List<Category>>>

    private val mealRepository = MealRepository(application)
    private val categoryRepository = CategoryRepository(application)

    init {
        BookRepository(application)
    }

    fun fetchMealsByCategory(category: String) {
        this.randomMealsLiveData = liveData(Dispatchers.IO) { emit(mealRepository.fetchMealsByCategory(category)) }
    }

    fun fetchMealById(id: String) {
        this.mealLiveData = liveData(Dispatchers.IO) { emit(mealRepository.fetchMealById(id)) }
    }

    fun fetchAllCategories() {
        this.categoriesLiveDate = liveData(Dispatchers.IO) { emit(categoryRepository.fetchAllCategories()) }
    }

    fun fetchMealsByName(name: String) {
        this.randomMealsLiveData = liveData(Dispatchers.IO) { emit(mealRepository.fetchMealsByName(name)) }
    }
}