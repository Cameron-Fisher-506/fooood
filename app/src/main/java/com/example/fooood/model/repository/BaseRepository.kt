package com.example.fooood.model.repository

import com.example.fooood.FoooodApplication
import com.example.fooood.model.service.FoooodService
import com.example.fooood.view.BaseActivity

abstract class BaseRepository {
    protected val foooodService: FoooodService = FoooodService()
    init {
        val baseActivity: BaseActivity = FoooodApplication.instance.topMostActivity
        baseActivity.displayProgressBar()
    }
}