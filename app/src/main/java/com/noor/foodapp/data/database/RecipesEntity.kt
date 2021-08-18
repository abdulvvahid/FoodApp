package com.noor.foodapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noor.foodapp.models.FoodRecipe
import com.noor.foodapp.util.Constants.Companion.RECIPES_TABLE

@Entity( tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}