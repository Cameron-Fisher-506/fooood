package com.example.fooood.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.fooood.model.models.Favourite

@Dao
interface IFavouriteDao : IBaseDao<Favourite> {

    @Query("SELECT * FROM favourite WHERE id = :id")
    suspend fun findById(id: String): Favourite?

    @Query("SELECT * FROM favourite")
    fun getAll(): LiveData<List<Favourite>>
}