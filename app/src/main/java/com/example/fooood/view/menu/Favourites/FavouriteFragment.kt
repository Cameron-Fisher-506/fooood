package com.example.fooood.view.menu.Favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fooood.R
import com.example.fooood.databinding.FavouriteFragmentBinding

class FavouriteFragment : Fragment(R.layout.favourite_fragment) {
    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var favouriteListAdapter: FavouriteListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = FavouriteFragmentBinding.bind(view)
    }
}