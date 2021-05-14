package com.example.fooood.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooood.databinding.MealItemBinding
import com.example.fooood.model.models.Meal

class SearchListAdapter(private val meals: ArrayList<Meal>) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    class ViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MealItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.root)
            .asBitmap()
            .load(meals[position].mealThumb)
            .into(holder.binding.mealImageView)

        holder.binding.foodNameTextView.text = meals[position].meal

        holder.binding.mealConstraintLayout.setOnClickListener {
            val action = SearchListFragmentDirections.actionSearchListFragmentToMealDetailsFragment2(meals[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = meals.size

    fun updateMealList(meals: List<Meal>) {
        this.meals.clear()
        this.meals.addAll(meals)
        this.notifyDataSetChanged()
    }
}