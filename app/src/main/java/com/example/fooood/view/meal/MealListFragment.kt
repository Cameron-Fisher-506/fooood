package com.example.fooood.view.meal

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooood.R
import com.example.fooood.databinding.MealListFragmentBinding
import com.example.fooood.enum.Status
import com.example.fooood.model.models.Category
import com.example.fooood.model.models.Meal
import com.example.fooood.utils.Resource
import kotlinx.android.synthetic.main.progress_bar.*

class MealListFragment: MealBaseFragment(R.layout.meal_list_fragment) {
    private lateinit var binding: MealListFragmentBinding
    private lateinit var mealViewModel: MealViewModel
    private lateinit var mealListAdapter: MealListAdapter
    private val categoryList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = MealListFragmentBinding.bind(view)

        this.mealViewModel = ViewModelProviders.of(this).get(MealViewModel::class.java)

        this.mealViewModel.fetchAllCategories()
        attachCategoryObserver()
        wireUI()
    }

    private fun attachRandomMealObserver() {
        this.mealViewModel.randomMealsLiveData.observe(viewLifecycleOwner, {
            mealActivity.dismissProgressBar()
            when (it.status) {
                Status.SUCCESS -> {
                    displayRecyclerView()

                    val data = it.data
                    if (data != null && data.isNotEmpty()) {
                        this.mealListAdapter.updateMealList(data)
                    } else {
                        displayMealErrorTextView()
                    }
                }
                Status.LOADING -> {  }
                Status.ERROR -> { displayMealErrorTextView() }
            }
        })
    }

    private fun attachCategoryObserver() {
        this.mealViewModel.categoriesLiveDate.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { categories ->

                        if (categories.isNotEmpty()) {
                            categoryList.clear()
                            categories.map { category -> categoryList.add(category.category) }

                            val c = context
                            if (c != null) {
                                val mealCategoriesAdapter: ArrayAdapter<String> = ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, categoryList)
                                mealCategoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                binding.mealCategorySpinner.adapter = mealCategoriesAdapter
                            }
                            this.mealViewModel.fetchMealsByName(categories.first().category)
                            attachRandomMealObserver()
                        }
                    }
                }
                Status.LOADING -> { }
                Status.ERROR -> {
                    mealActivity.dismissProgressBar()
                    displayMealErrorTextView()
                }
            }
        })
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
                mealViewModel.fetchMealsByCategory("Beef")
                mealSwipeRefreshLayout.isRefreshing = false
            }

            context?.let {
                categoryList.clear()
                val mealCategoriesAdapter: ArrayAdapter<String> = ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.mealCategories))
                mealCategoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                mealCategorySpinner.adapter = mealCategoriesAdapter
            }

            mealCategorySpinner.onItemSelectedListener = object: AdapterView.OnItemClickListener,
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (categoryList.isNotEmpty()) {
                        mealViewModel.fetchMealsByCategory(categoryList[position])
                        attachRandomMealObserver()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    TODO("Not yet implemented")
                }

            }

        }
    }
}