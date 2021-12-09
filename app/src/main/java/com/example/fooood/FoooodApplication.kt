package com.example.fooood

import android.app.Application
import com.example.fooood.view.BaseActivity

class FoooodApplication : Application() {
    lateinit var topMostActivity: BaseActivity

    companion object {
        lateinit var instance: FoooodApplication
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(FoooodApplicationLifecycleObserver(this))
        instance = this
    }
}