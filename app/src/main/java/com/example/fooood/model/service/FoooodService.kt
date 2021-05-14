package com.example.fooood.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoooodService: BaseService() {
    private lateinit var api: IFoooodApi

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.api = retrofit.create(IFoooodApi::class.java)
    }

    suspend fun getMealsByName(name: String) = getResource { api.getMealsByName(name) }

    suspend fun getMealsByCategory(category: String) = getResource { api.getMealsByCategory(category) }

    suspend fun getMealById(id: String) = getResource { api.getMealById(id) }

}