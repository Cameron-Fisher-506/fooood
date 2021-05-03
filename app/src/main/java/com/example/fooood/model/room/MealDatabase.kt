package com.example.fooood.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooood.model.models.Book
import com.example.fooood.model.models.Meal

@Database(entities = [Book::class, Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {
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
    }
}