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
                if(response.status == Status.SUCCESS && response.data != null) {
                    if (response.data is BookWithMeals) {
                        mealDao.insert(response.data.meals)
                        emit(Resource.success(response.data.meals))
                    }
                } else {
                    emit(Resource.error("No"))
                }
            }
        }

    inline fun <T> synchronizedCache(mealDao: IMealDao, value: String?, crossinline wsCall: suspend () -> Resource<T>) =
        liveData<Resource<List<Meal>>>(Dispatchers.IO) {
            emit(Resource.loading())

            var result: List<Meal>? = null
            result = if (value == null) {
                mealDao.getRandomMeals()
            } else {
                mealDao.getAllByValue(value)
            }

            if(result != null && result.isNotEmpty()) {
                var mustUpdate = false
                for(i in result.indices) {
                    if(DateTimeUtils.differenceInMinutes(result[i].timestamp, DateTimeUtils.getCurrentDateTime()) > DateTimeUtils.ONE_MINUTE) {
                        mustUpdate = true
                        break
                    }
                }

                if (mustUpdate) {
                    val response  = wsCall.invoke()
                    if(response.status == Status.SUCCESS && response.data != null) {
                        if (response.data is BookWithMeals) {
                            mealDao.upsert(response.data.meals, mealDao)
                            emit(Resource.success(response.data.meals))
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
                    if(response.data is BookWithMeals) {
                        mealDao.upsert(response.data.meals, mealDao)
                        emit(Resource.success(response.data.meals))
                    }
                } else {
                    emit(Resource.error("No data found."))
                }
            }
        }
}