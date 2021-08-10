package com.example.fooood.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.fooood.model.models.Meal
import com.example.fooood.model.repository.MealRepository
import com.example.fooood.utils.Resource

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var mealsBySearchLiveData: LiveData<Resource<List<Meal>>>

    private val mealRepository = MealRepository(application)

    fun getMealsByName(name: String) {
        mealsBySearchLiveData = liveData(Dispatchers.IO) { emit(mealRepository.fetchMealsByName(name)) }
    }
}