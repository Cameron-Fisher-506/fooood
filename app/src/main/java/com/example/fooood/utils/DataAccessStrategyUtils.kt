package com.example.fooood.utils

import androidx.lifecycle.liveData
import com.example.fooood.enum.Status
import com.example.fooood.model.models.BookWithMeals
import com.example.fooood.model.models.Meal
import com.example.fooood.model.room.IBaseDao
import com.example.fooood.model.room.IMealDao
import com.example.fooood.model.room.upsert
import kotlinx.coroutines.Dispatchers

object DataAccessStrategyUtils {
    inline fun <T> lazyCache(mealDao: IMealDao, value: String, crossinline wsCall: suspend () -> Resource<T>) =
        liveData<Resource<List<Meal>>>(Dispatchers.IO) {
            emit(Resource.loading())

            val result = mealDao.getAllByValue(value)
            if (result != null) {
                emit(Resource.success(result))
            } else {
                val response = wsCall.invoke()
                if (response.status == Status.SUCCESS && response.data != null) {
                    if (response.data is BookWithMeals) {
                        val meals = response.data.meals
                        if (meals != null) {
                            mealDao.insert(meals)
                            emit(Resource.success(meals))
                        } else {
                            emit(Resource.error("No meals found."))
                        }
                    }
                } else {
                    emit(Resource.error("No meals found."))
                }
            }
        }

    inline fun <T> synchronizedCache(mealDao: IMealDao, id: String?, crossinline wsCall: suspend () -> Resource<T>) =
        liveData<Resource<List<Meal>>>(Dispatchers.IO) {
            emit(Resource.loading())

            var result: List<Meal>? = null
            result = if (id == null) {
                mealDao.getRandom()
            } else {
                mealDao.getById(id)
            }

            if (result != null && result.isNotEmpty()) {
                var mustUpdate = false
                for (i in result.indices) {
                    if (DateTimeUtils.differenceInMinutes(result[i].timestamp, DateTimeUtils.getCurrentDateTime()) > DateTimeUtils.ONE_MINUTE) {
                        mustUpdate = true
                        break
                    }
                }

                if (mustUpdate) {
                    val response = wsCall.invoke()
                    if (response.status == Status.SUCCESS && response.data != null) {
                        when (response.data) {
                            is BookWithMeals -> {
                                val meals = response.data.meals
                                if (meals != null) {
                                    mealDao.upsert(meals, mealDao)
                                    emit(Resource.success(meals))
                                } else {
                                    emit(Resource.error("No meals found."))
                                }
                            }
                            is Meal -> {
                                mealDao.upsert(response.data, mealDao)

                                val meals = listOf(response.data)
                                emit(Resource.success(meals))
                            }
                            else -> { emit(Resource.error("No data found.")) }
                        }
                    } else {
                        emit(Resource.error("No Data Found."))
                    }
                } else {
                    emit(Resource.success(result))
                }
            } else {
                val response = wsCall.invoke()
                if (response.status == Status.SUCCESS && response.data != null) {
                    when (response.data) {
                        is BookWithMeals -> {
                            val meals = response.data.meals
                            if (meals != null) {
                                mealDao.upsert(meals, mealDao)
                                emit(Resource.success(meals))
                            } else {
                                emit(Resource.error("No meals found."))
                            }
                        }
                        is Meal -> {
                            mealDao.upsert(response.data, mealDao)

                            val meals = listOf(response.data)
                            emit(Resource.success(meals))
                        }
                        else -> { emit(Resource.error("No data found.")) }
                    }
                } else {
                    emit(Resource.error("No data found."))
                }
            }
        }
}