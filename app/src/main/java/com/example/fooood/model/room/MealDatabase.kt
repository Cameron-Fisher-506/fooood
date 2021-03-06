package com.example.fooood.model.room

import android.content.Context
import androidx.lifecycle.liveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooood.model.models.Book
import com.example.fooood.model.models.Category
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.models.Meal
import com.example.fooood.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

@Database(entities = [Book::class, Meal::class, Favourite::class, Category::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {

    abstract fun bookDao(): IBookDao
    abstract fun mealDao(): IMealDao
    abstract fun favouriteDao(): IFavouriteDao
    abstract fun categoryDao(): ICategoryDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MealDatabase::class.java,
                        "fooood"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }

        inline fun <T> getLiveDataResource(crossinline daoCall: suspend () -> T?) =
            liveData<Resource<T>>(Dispatchers.IO) {
                emit(Resource.loading())

                val value = daoCall.invoke()
                if (value != null) {
                    emit(Resource.success(value))
                } else {
                    emit(Resource.error("Not cached."))
                }
            }


        suspend inline fun <T> getResource(crossinline daoCall: suspend () -> T?): Resource<T> {
            try {
                val response = daoCall.invoke()
                if (response != null) {
                    return Resource.success(response)
                }
                return Resource.error("Not found in cache.")
            } catch (e: Exception) {
                return Resource.error(e.message ?: e.toString())
            }
        }
    }
}