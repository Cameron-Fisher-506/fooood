package com.example.fooood.view.meal

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

abstract class MealBaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected lateinit var mealActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mealActivity = context as MainActivity
    }
}