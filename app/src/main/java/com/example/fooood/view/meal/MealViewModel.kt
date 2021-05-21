package com.example.fooood.view.meal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    fun getMealsByCategory(category: String) {
        this.randomMealsLiveData = this.mealRepository.getMealsByCategory(category)
    }

    fun getMealById(id: String) {
        mealLiveData = mealRepository.getMealById(id)
    }

    fun getAllCategories(update: Boolean) {
        categoriesLiveDate = categoryRepository.getAllCategories(update)
    }
}