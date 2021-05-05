package com.example.fooood.view.meal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fooood.R
import com.example.fooood.databinding.MealDetailsFragmentBinding

class MealDetailsFragment : Fragment(R.layout.meal_details_fragment) {
    private lateinit var binding: MealDetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.binding = MealDetailsFragmentBinding.bind(view)
        arguments?.let { wireUI(it) }
    }

    private fun wireUI(bundle: Bundle) {
        val meal = MealDetailsFragmentArgs.fromBundle(bundle).meal

        with (this.binding) {
            Glide.with(this.root)
                .asBitmap()
                .load(meal.mealThumb)
                .into(mealImageView)

            mealTextView.text = meal.meal
        }
    }
}