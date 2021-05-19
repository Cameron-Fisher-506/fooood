package com.example.fooood.view.meal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooood.R
import com.example.fooood.databinding.MealListFragmentBinding
import com.example.fooood.enum.Status
import com.example.fooood.model.models.Meal
import com.example.fooood.utils.Resource

class MealListFragment: Fragment(R.layout.meal_list_fragment) {
    private lateinit var binding: MealListFragmentBinding
    private lateinit var mealViewModel: MealViewModel
    private lateinit var mealListAdapter: MealListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = MealListFragmentBinding.bind(view)

        this.mealViewModel = ViewModelProviders.of(this).get(MealViewModel::class.java)

        this.mealViewModel.getMealsByCategory("Dessert")
        wireUI()
        attachObservers()
    }

    private fun attachObservers() {
        val randomMealObserver = Observer<Resource<List<Meal>>> {
            when (it.status) {
                Status.SUCCESS -> {
                    displayRecyclerView()
                    it.data?.let { data -> if (data.isNotEmpty()) this.mealListAdapter.updateMealList(data) else displayMealErrorTextView() }
                }
                Status.LOADING -> { displayMealProgressBar() }
                Status.ERROR -> { displayMealErrorTextView() }
            }
        }

        this.mealViewModel.randomMealsLiveData.observe(this, randomMealObserver)
    }

    private fun displayRecyclerView() {
        with (this.binding) {
            mealRecyclerView.visibility = View.VISIBLE
            mealErrorTextView.visibility = View.GONE
            mealProgressBar.visibility = View.GONE
        }
    }

    private fun displayMealErrorTextView() {
        with (this.binding) {
            mealRecyclerView.visibility = View.GONE
            mealErrorTextView.visibility = View.VISIBLE
            mealProgressBar.visibility = View.GONE
        }
    }

    private fun displayMealProgressBar() {
        with (this.binding) {
            mealRecyclerView.visibility = View.GONE
            mealErrorTextView.visibility = View.GONE
            mealProgressBar.visibility = View.VISIBLE
        }
    }

    private fun wireUI() {
        this.mealListAdapter = MealListAdapter(arrayListOf())
        with (this.binding) {
            mealRecyclerView.layoutManager = GridLayoutManager(context, 1)
            mealRecyclerView.adapter = mealListAdapter

            mealSwipeRefreshLayout.setOnRefreshListener {
                displayMealProgressBar()
                mealViewModel.getMealsByCategory("Pasta")
                mealSwipeRefreshLayout.isRefreshing = false
            }
        }
    }
}