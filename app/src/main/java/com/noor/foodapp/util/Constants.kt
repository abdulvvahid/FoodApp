package com.noor.foodapp.util

class Constants {

    // Url Search = https://api.spoonacular.com/recipes/complexSearch?number=1&apiKey=ffc510c8409140bfa6bdf5da20f0edda&type=drink&diet=vegan&addRecipeInformation=true&fillIngredients=true

    companion object {
        const val API_KEY = "ffc510c8409140bfa6bdf5da20f0edda"
        const val BASE_URL = "https://api.spoonacular.com"

        // API Query Keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY= API_KEY
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"
    }

}