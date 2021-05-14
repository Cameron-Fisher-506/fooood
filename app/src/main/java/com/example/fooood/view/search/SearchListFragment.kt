package com.example.fooood.view.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooood.R
import com.example.fooood.databinding.SearchListFragmentBinding
import com.example.fooood.enum.Status
import com.example.fooood.model.models.Meal
import com.example.fooood.utils.GeneralUtils
import com.example.fooood.utils.Resource
import kotlinx.android.synthetic.main.search_list_fragment.*

class SearchListFragment : Fragment(R.layout.search_list_fragment) {
    private lateinit var binding: SearchListFragmentBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchListAdapter: SearchListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = SearchListFragmentBinding.bind(view)

        this.searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        this.searchViewModel.getMealsByName("null")
        attachObservers()
        wireUI()
    }

    private fun attachObservers() {
        val mealsBySearchObserver = Observer<Resource<List<Meal>>> {
            when (it.status) {
                Status.SUCCESS -> {
                    showSearchRecyclerView()
                    it.data?.let { data -> this.searchListAdapter.updateMealList(data) }
                }
                Status.ERROR -> showError()
                Status.LOADING -> showLoading()
            }
        }
        this.searchViewModel.mealsBySearchLiveData.observe(this, mealsBySearchObserver)
    }

    private fun wireUI() {
        this.binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN || event.action == KeyEvent.KEYCODE_ENTER) {
                showLoading()
                searchViewModel.getMealsByName(searchEditText.text.toString())
            }
            false
        }

        this.binding.searchImageButton.setOnClickListener {
            if(searchEditText.text.toString().isNotEmpty()) {
                showLoading()
                searchViewModel.getMealsByName(searchEditText.text.toString())
            } else {
                context?.let { GeneralUtils.makeToast(it, "Please enter a search term.") }
            }
        }

        this.searchListAdapter = SearchListAdapter(arrayListOf())
        this.searchRecyclerView.layoutManager = GridLayoutManager(context, 1)
        this.searchRecyclerView.adapter = this.searchListAdapter
    }

    private fun showLoading() {
        with(binding) {
            loadingProgressBar.visibility = View.VISIBLE
            searchRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.GONE
        }
    }

    private fun showSearchRecyclerView() {
        with(binding) {
            loadingProgressBar.visibility = View.GONE
            searchRecyclerView.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            loadingProgressBar.visibility = View.GONE
            searchRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.VISIBLE
        }
    }
}