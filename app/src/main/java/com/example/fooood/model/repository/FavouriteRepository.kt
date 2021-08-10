package com.example.fooood.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.room.MealDatabase
import com.example.fooood.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteRepository(application: Application) {
    private val favouritesDao = MealDatabase.getDatabase(application).favouriteDao()

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
        return MealDatabase.getLiveDataResource { favouritesDao.findById(id) }
    }

    fun fetchAll(): LiveData<Resource<List<Favourite>>> {
        return MealDatabase.getLiveDataResource { favouritesDao.getAll() }
    }
}