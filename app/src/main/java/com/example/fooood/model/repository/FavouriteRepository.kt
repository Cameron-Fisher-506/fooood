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
    private val findByIdLiveData by lazy { MutableLiveData<String>() }
    private val updateLiveData by lazy { MutableLiveData<Boolean>() }

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

    fun findById(id: String): LiveData<Resource<Favourite>> {
        findByIdLiveData.value = id
        return Transformations.switchMap(findByIdLiveData) { MealDatabase.getLiveDataResource { favouritesDao.findById(it) } }
    }

    fun getAll(update: Boolean): LiveData<Resource<List<Favourite>>> {
        updateLiveData.value = update
        return Transformations.switchMap(updateLiveData) { MealDatabase.getLiveDataResource { favouritesDao.getAll() } }
    }
}