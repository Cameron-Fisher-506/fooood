package com.example.fooood.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fooood.model.models.Meal
import com.example.fooood.model.repository.MealRepository
import com.example.fooood.utils.Resource

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var mealsBySearchLiveData: LiveData<Resource<List<Meal>>>

    private val mealRepository = MealRepository(application)

    fun getMealsByName(name: String) {
        //mealsBySearchLiveData = mealRepository.getMealsByName(name)
    }
}