package com.example.fooood.model.service

import com.example.fooood.model.models.BookWithMeals
import com.example.fooood.model.models.Meal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IFoooodApi {
    @GET("search.php")
    suspend fun getByName(@Query("s") s: String): Response<Meal>

    @GET("search.php")
    suspend fun getByFirstLetter(@Query("f") f: String): Response<Meal>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<Meal>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") c: String): Response<BookWithMeals>
}