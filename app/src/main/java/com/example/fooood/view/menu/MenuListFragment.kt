package com.example.fooood.view.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fooood.R
import com.example.fooood.databinding.MenuListFragmentBinding

class MenuListFragment : Fragment(R.layout.menu_list_fragment) {
    private lateinit var binding: MenuListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = MenuListFragmentBinding.bind(view)

        wireUI()
    }

    private fun wireUI() {
        this.binding.favouritesMaterialCardView.setOnClickListener {
            val action = MenuListFragmentDirections.actionMenuListFragmentToFavouriteFragment()
            Navigation.findNavController(it).navigate(action)
        }

        this.binding.appMaterialCardView.setOnClickListener {
            val action = MenuListFragmentDirections.actionMenuListFragmentToAppFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}