package com.example.fooood.view.meal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.fooood.R
import com.example.fooood.databinding.MealDetailsFragmentBinding
import com.example.fooood.enum.Status
import com.example.fooood.model.models.Meal
import com.example.fooood.utils.Resource

class MealDetailsFragment : Fragment(R.layout.meal_details_fragment) {
    private lateinit var binding: MealDetailsFragmentBinding
    private lateinit var mealViewModel: MealViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = MealDetailsFragmentBinding.bind(view)

        this.mealViewModel = ViewModelProviders.of(this).get(MealViewModel::class.java)

        arguments?.let {
            val meal = MealDetailsFragmentArgs.fromBundle(it).meal
            mealViewModel.getMealById(meal.id)
        }
        attachObservers()
    }

    private fun attachObservers() {
        val mealObserver = Observer<Resource<List<Meal>>> {
            when (it.status) {
                Status.SUCCESS -> {
                    displayMealDetails()
                    if (it.data != null) {
                        if (it.data.isNotEmpty()) {
                            wireUI(it.data.first())
                        } else {
                            displayMealErrorTextView()
                        }
                    } else {
                        displayMealErrorTextView()
                    }
                }
                Status.ERROR -> { displayMealErrorTextView() }
                Status.LOADING -> { displayMealProgressBar() }
            }
        }
        mealViewModel.mealLiveData.observe(this, mealObserver)
    }

    private fun displayMealDetails() {
        with (this.binding) {
            mealImageView.visibility = View.VISIBLE
            mealTextView.visibility = View.VISIBLE
            mealProgressBar.visibility = View.GONE
            mealErrorTextView.visibility = View.GONE
        }
    }

    private fun hideMealDetials() {
        with (this.binding) {
            mealTextView.visibility = View.GONE
            mealImageView.visibility = View.GONE
        }
    }

    private fun displayMealErrorTextView() {
        hideMealDetials()
        with (this.binding) {
            mealErrorTextView.visibility = View.VISIBLE
            mealProgressBar.visibility = View.GONE
        }
    }

    private fun displayMealProgressBar() {
        hideMealDetials()
        with (this.binding) {
            mealErrorTextView.visibility = View.GONE
            mealProgressBar.visibility = View.VISIBLE
        }
    }

    private fun wireUI(meal: Meal) {
        with (this.binding) {
            Glide.with(this.root)
                .asBitmap()
                .load(meal.mealThumb)
                .into(mealImageView)

            mealTextView.text = meal.meal
        }
    }
}