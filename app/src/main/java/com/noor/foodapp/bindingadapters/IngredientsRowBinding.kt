package com.noor.foodapp.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.noor.foodapp.util.Constants.Companion.BASE_IMAGE_URL
import com.noor.foodapp.R

class IngredientsRowBinding {

    companion object {
        @BindingAdapter("loadIngredientImageFromUrl")
        @JvmStatic
        fun loadIngredientImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(BASE_IMAGE_URL + imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("setNumberOfAmount")
        @JvmStatic
        fun setNumberOfAmount(textView: TextView, amount: Double) {
            textView.text = amount.toString()
        }
    }

}