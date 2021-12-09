package com.example.fooood

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.fooood.view.BaseActivity

class FoooodApplicationLifecycleObserver(private val foooodApplication: FoooodApplication) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity is BaseActivity) {
            foooodApplication.topMostActivity = activity
        }
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is BaseActivity) {
            foooodApplication.topMostActivity = activity
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}