package com.noor.foodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.noor.foodapp.databinding.IngredientsRowLayoutBinding
import com.noor.foodapp.models.ExtendedIngredient
import com.noor.foodapp.util.RecipesDiffUtil


class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    class MyViewHolder(
        private val binding: IngredientsRowLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(extendedIngredient: ExtendedIngredient) {
            binding.ingredient = extendedIngredient
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return IngredientsAdapter.MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentIngredient = ingredientsList[position]
        holder.bind(currentIngredient)
    }

    override fun getItemCount(): Int = ingredientsList.size

    fun setData(newData: List<ExtendedIngredient>) {
        val ingredientsDiffUtil = RecipesDiffUtil(ingredientsList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}