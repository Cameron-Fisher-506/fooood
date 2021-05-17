package com.example.fooood.view.menu.Favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooood.R
import com.example.fooood.databinding.FavouriteFragmentBinding
import com.example.fooood.enum.Status
import com.example.fooood.model.models.Favourite
import com.example.fooood.utils.Resource
import kotlinx.android.synthetic.main.favourite_fragment.*

class FavouriteFragment : Fragment(R.layout.favourite_fragment) {
    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var favouriteListAdapter: FavouriteListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = FavouriteFragmentBinding.bind(view)

        this.favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel::class.java)

        wireUI()
        attachObservers()
    }

    private fun attachObservers() {
        val getAllObserver = Observer<Resource<List<Favourite>>> {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        showFavouriteRecyclerView()
                        this.favouriteListAdapter.updateFavouriteList(it.data)
                    } else {
                        showErrorMessageTextView()
                    }
                }
                Status.ERROR -> showErrorMessageTextView()
                Status.LOADING -> showFavouriteProgressBar()
            }
        }
        this.favouriteViewModel.getAllLiveData.observe(this, getAllObserver)
    }

    private fun showFavouriteRecyclerView() {
        with(this.binding) {
            favouriteRecyclerView.visibility = View.VISIBLE
            favouriteProgressBar.visibility = View.GONE
            errorMessageTextView.visibility = View.GONE
        }
    }

    private fun showErrorMessageTextView() {
        with(this.binding) {
            favouriteRecyclerView.visibility = View.GONE
            favouriteProgressBar.visibility = View.GONE
            errorMessageTextView.visibility = View.VISIBLE
        }
    }

    private fun showFavouriteProgressBar() {
        with(this.binding) {
            favouriteRecyclerView.visibility = View.GONE
            favouriteProgressBar.visibility = View.VISIBLE
            errorMessageTextView.visibility = View.GONE
        }
    }

    private fun wireUI() {
        this.favouriteListAdapter = FavouriteListAdapter(arrayListOf())
        this.binding.favouriteRecyclerView.layoutManager = GridLayoutManager(context, 1)
        this.binding.favouriteRecyclerView.adapter = this.favouriteListAdapter

        this.binding.favouriteSwipeRefreshLayout.setOnRefreshListener {
            favouriteViewModel.getAll(true)
            favouriteSwipeRefreshLayout.isRefreshing = false
        }
    }
}