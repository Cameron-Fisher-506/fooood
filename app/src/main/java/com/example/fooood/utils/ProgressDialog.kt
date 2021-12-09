package com.example.fooood.utils

import android.app.Dialog
import android.content.Context
import com.example.fooood.R

class ProgressDialog(context: Context) : Dialog(context) {

    init {
        this.setContentView(R.layout.progress_bar)
        this.show()
    }
}