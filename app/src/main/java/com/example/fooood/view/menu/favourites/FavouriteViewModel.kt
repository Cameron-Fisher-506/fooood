package com.example.fooood.view.menu.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.repository.FavouriteRepository
import com.example.fooood.utils.Resource

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val favouriteRepository = FavouriteRepository(application)
    lateinit var findByIdLiveData: LiveData<Resource<Favourite>>
    lateinit var getAllLiveData: LiveData<Resource<List<Favourite>>>

    init {
        getAll(true)
    }

    fun insert(favourite: Favourite) {
        favouriteRepository.insert(favourite)
    }

    fun delete(favourite: Favourite) {
        favouriteRepository.delete(favourite)
    }

    fun findById(id: String) {
        findByIdLiveData = favouriteRepository.findById(id)
    }

    fun getAll(update: Boolean) {
        getAllLiveData = favouriteRepository.getAll(update)
    }
}