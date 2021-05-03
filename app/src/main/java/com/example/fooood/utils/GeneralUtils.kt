package com.example.fooood.utils

import android.content.Context
import android.widget.Toast

object GeneralUtils {
    fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}