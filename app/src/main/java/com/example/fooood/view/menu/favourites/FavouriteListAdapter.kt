package com.example.fooood.view.menu.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooood.databinding.MealItemBinding
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.models.Meal

class FavouriteListAdapter(private val favourites: ArrayList<Favourite>) : RecyclerView.Adapter<FavouriteListAdapter.ViewHolder>() {
    class ViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MealItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.root)
            .asBitmap()
            .load(favourites[position].mealThumb)
            .into(holder.binding.mealImageView)

        holder.binding.foodNameTextView.text = favourites[position].meal
        holder.binding.mealConstraintLayout.setOnClickListener {
            val meal = Meal().apply {
                id = favourites[position].id
                meal = favourites[position].meal
                drinkAlternate = favourites[position].drinkAlternate
                category = favourites[position].category
                area = favourites[position].area
                instructions = favourites[position].instructions
                mealThumb = favourites[position].mealThumb
                tags = favourites[position].tags
                youTube = favourites[position].youTube
                ingredientOne = favourites[position].ingredientOne
                ingredientTwo = favourites[position].ingredientTwo
                ingredientThree = favourites[position].ingredientThree
                ingredientFour = favourites[position].ingredientFour
                ingredientFive = favourites[position].ingredientFive
                ingredientSix = favourites[position].ingredientSix
                ingredientSeven = favourites[position].ingredientSeven
                ingredientEight = favourites[position].ingredientEight
                ingredientNine = favourites[position].ingredientNine
                ingredientTen = favourites[position].ingredientTen
                ingredientEleven = favourites[position].ingredientEleven
                ingredientTwelve = favourites[position].ingredientTwelve
                ingredientThirteen = favourites[position].ingredientThirteen
                ingredientFourteen = favourites[position].ingredientFourteen
                ingredientFifteen = favourites[position].ingredientFifteen
                ingredientSixteen = favourites[position].ingredientSixteen
                ingredientSeventeen = favourites[position].ingredientSeventeen
                ingredientEighteen = favourites[position].ingredientEighteen
                ingredientNineteen = favourites[position].ingredientNineteen
                ingredientTwenty = favourites[position].ingredientTwenty
                measurementOne = favourites[position].measurementOne
                measurementTwo = favourites[position].measurementTwo
                measurementThree = favourites[position].measurementThree
                measurementFour = favourites[position].measurementFour
                measurementFive = favourites[position].measurementFive
                measurementSix = favourites[position].measurementSix
                measurementSeven = favourites[position].measurementSeven
                measurementEight = favourites[position].measurementEight
                measurementNine = favourites[position].measurementNine
                measurementTen = favourites[position].measurementTen
                measurementEleven = favourites[position].measurementEleven
                measurementTwelve = favourites[position].measurementTwelve
                measurementThirteen = favourites[position].measurementThirteen
                measurementFourteen = favourites[position].measurementFourteen
                measurementFifteen = favourites[position].measurementFifteen
                measurementSixteen = favourites[position].measurementSixteen
                measurementSeventeen = favourites[position].measurementSeventeen
                measurementEighteen = favourites[position].measurementEighteen
                measurementNineteen = favourites[position].measurementNineteen
                measurementTwenty = favourites[position].measurementTwenty
                source = favourites[position].source
                imageSource = favourites[position].imageSource
                creativeCommonsConfirmed = favourites[position].creativeCommonsConfirmed
                dateModified = favourites[position].dateModified
                bookId = favourites[position].bookId
                timestamp = favourites[position].timestamp
            }

            val action = FavouriteFragmentDirections.actionFavouriteFragmentToMealDetailsFragment3(meal)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = favourites.size

    fun updateFavouriteList(favourites: List<Favourite>) {
        this.favourites.clear()
        this.favourites.addAll(favourites)
        this.notifyDataSetChanged()
    }
}