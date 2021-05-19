package com.example.fooood.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.fooood.enum.Status
import com.example.fooood.model.models.BookWithMeals
import com.example.fooood.model.models.Meal
import com.example.fooood.model.room.IBaseDao
import com.example.fooood.model.room.IMealDao
import com.example.fooood.model.room.upsert
import kotlinx.coroutines.Dispatchers

object DataAccessStrategyUtils {
    inline fun <A, T> lazyCache(crossinline dbQuery: () -> LiveData<Resource<T>>, crossinline wsCall: suspend () -> Resource<A>, crossinline saveCall: suspend (A) -> Unit) =
        liveData<Resource<T>>(Dispatchers.IO) {
            emit(Resource.loading())

            val result = dbQuery.invoke()
            emitSource(result)

            val response = wsCall.invoke()
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { saveCall(it) }
                }
                Status.ERROR -> emit(Resource.error(response.message))
                else -> emit(Resource.error("No data found."))
            }
        }

    inline fun <A, T> synchronizedCache(crossinline dbQuery: () -> LiveData<Resource<T>>, crossinline wsCall: suspend () -> Resource<A>, crossinline saveCall: suspend (A) -> Unit) =
        liveData<Resource<T>>(Dispatchers.IO) {
            emit(Resource.loading())

            val result = dbQuery.invoke()
            emitSource(result)

            val response = wsCall.invoke()
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { saveCall(it) }
                }
                Status.ERROR -> emit(Resource.error(response.message))
                else -> emit(Resource.error("No data found."))
            }

            /*if (result != null && result.isNotEmpty()) {
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
            }*/
        }
}