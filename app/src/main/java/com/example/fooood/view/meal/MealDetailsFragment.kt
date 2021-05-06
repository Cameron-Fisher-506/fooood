package com.example.fooood.view.meal

import android.os.Bundle
import android.text.Html
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
import com.example.fooood.utils.TextUtils
import java.lang.StringBuilder

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
            ingredientsOneTextView.visibility = View.VISIBLE
            ingredientsTwoTextView.visibility = View.VISIBLE
            mealProgressBar.visibility = View.GONE
            mealErrorTextView.visibility = View.GONE
        }
    }

    private fun hideMealDetials() {
        with (this.binding) {
            mealTextView.visibility = View.GONE
            mealImageView.visibility = View.GONE
            ingredientsOneTextView.visibility = View.GONE
            ingredientsTwoTextView.visibility = View.GONE
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

    private fun buildIngredientsListItem(ingredient: String?, measurement: String?): String =
        if (ingredient != null && ingredient.isNotEmpty() && measurement != null && measurement.isNotEmpty()) { "${TextUtils.BULLET_POINT} $ingredient ($measurement)<br/>" } else ""

    private fun wireUI(meal: Meal) {
        with (this.binding) {
            Glide.with(this.root)
                .asBitmap()
                .load(meal.mealThumb)
                .into(mealImageView)

            mealTextView.text = meal.meal

            val ingredientsListOne = StringBuilder()
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientOne, meal.measurementOne))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientTwo, meal.measurementTwo))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientThree, meal.measurementThree))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientFour, meal.measurementFour))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientFive, meal.measurementFive))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientSix, meal.measurementSix))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientSeven, meal.measurementSeven))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientEight, meal.measurementEight))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientNine, meal.measurementNine))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientTen, meal.measurementTen))
            ingredientsOneTextView.text = Html.fromHtml(ingredientsListOne.toString())

            val ingredientsListTwo = StringBuilder()
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientEleven, meal.measurementEleven))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientTwelve, meal.measurementTwelve))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientThirteen, meal.measurementThirteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientFourteen, meal.measurementFourteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientFifteen, meal.measurementFifteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientSixteen, meal.measurementSixteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientSeventeen, meal.measurementSeventeen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientEighteen, meal.measurementEighteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientNineteen, meal.measurementNineteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientTwenty, meal.measurementTwenty))
            ingredientsTwoTextView.text = Html.fromHtml(ingredientsListTwo.toString())
        }
    }
}