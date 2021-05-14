package com.example.fooood.view.menu.Favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.repository.FavouriteRepository
import com.example.fooood.utils.Resource

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    private val favouriteRepository = FavouriteRepository(application)
    lateinit var findByIdLiveData: LiveData<Resource<Favourite>>

    fun insert(favourite: Favourite) {
        favouriteRepository.insert(favourite)
    }

    fun delete(favourite: Favourite) {
        favouriteRepository.delete(favourite)
    }

    fun findById(id: String) {
        findByIdLiveData = favouriteRepository.findById(id)
    }
}