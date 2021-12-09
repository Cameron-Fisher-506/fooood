package com.example.fooood.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooood.utils.ProgressDialog

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar = ProgressDialog(this)
    }

    fun displayProgressBar() {
        progressBar.show()
    }

    fun dismissProgressBar() {
        progressBar.dismiss()
    }
}

