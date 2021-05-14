package com.example.fooood.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.room.MealDatabase
import com.example.fooood.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteRepository(application: Application) {
    private val favouritesDao = MealDatabase.getDatabase(application).favouriteDao()
    private val findByValueLiveData by lazy { MutableLiveData<String>() }

    fun insert(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDao.insert(favourite)
        }
    }

    fun delete(favourite: Favourite) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDao.delete(favourite)
        }
    }

    fun findByValue(value: String): LiveData<Resource<Favourite>> {
        findByValueLiveData.value = value
        return Transformations.switchMap(findByValueLiveData) { MealDatabase.getResource { favouritesDao.findByName(it) } }
    }
}