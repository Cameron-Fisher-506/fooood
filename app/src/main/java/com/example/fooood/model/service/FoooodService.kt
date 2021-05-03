package com.example.fooood.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoooodService: BaseService() {
    private lateinit var api: IFoooodApi

    companion object {
        const val BASE_URL = "www.themealdb.com/api/json/v1/1/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.api = retrofit.create(IFoooodApi::class.java)
    }


}