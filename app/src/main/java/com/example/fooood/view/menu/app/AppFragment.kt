package com.example.fooood.view.menu.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fooood.R
import com.example.fooood.databinding.AppFragmentBinding

class AppFragment : Fragment(R.layout.app_fragment) {
    private lateinit var binding: AppFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = AppFragmentBinding.bind(view)
    }
}