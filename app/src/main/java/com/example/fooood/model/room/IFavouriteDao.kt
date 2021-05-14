package com.example.fooood.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.fooood.model.models.Favourite

@Dao
interface IFavouriteDao : IBaseDao<Favourite> {

    @Query("SELECT * FROM favourite WHERE meal = :name")
    suspend fun findByName(name: String): Favourite?

    @Query("SELECT * FROM favourite")
    fun getAll(): LiveData<List<Favourite>>
}