package com.noor.foodapp.models

import com.google.gson.annotations.SerializedName

data class FoodJoke(
    @SerializedName("text")
    val text: String
)