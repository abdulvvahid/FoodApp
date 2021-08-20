package com.noor.foodapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.noor.foodapp.models.Result
import com.noor.foodapp.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)