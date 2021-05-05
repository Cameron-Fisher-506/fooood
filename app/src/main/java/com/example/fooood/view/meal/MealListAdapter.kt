package com.example.fooood.view.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooood.databinding.MealItemBinding
import com.example.fooood.model.models.Meal

class MealListAdapter(private val mealList: ArrayList<Meal>) : RecyclerView.Adapter<MealListAdapter.ViewHolder>() {

    class ViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MealItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.root)
            .asBitmap()
            .load(mealList[position].mealThumb)
            .into(holder.binding.mealImageView)

        holder.binding.foodNameTextView.text = mealList[position].meal
    }

    override fun getItemCount(): Int = mealList.size

    fun updateMealList(meals: List<Meal>) {
        this.mealList.clear()
        this.mealList.addAll(meals)
        notifyDataSetChanged()
    }
}