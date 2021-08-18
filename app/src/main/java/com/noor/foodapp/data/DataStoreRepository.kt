package com.noor.foodapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.noor.foodapp.data.DataStoreRepository.PreferenceKeys.selectedDietType
import com.noor.foodapp.data.DataStoreRepository.PreferenceKeys.selectedDietTypeId
import com.noor.foodapp.data.DataStoreRepository.PreferenceKeys.selectedMealType
import com.noor.foodapp.data.DataStoreRepository.PreferenceKeys.selectedMealTypeId
import com.noor.foodapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.noor.foodapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.noor.foodapp.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.noor.foodapp.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.noor.foodapp.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.noor.foodapp.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.noor.foodapp.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
    }

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCES_NAME
    )

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        context.dataStore.edit { preferences ->
            preferences[selectedMealType] = mealType
            preferences[selectedMealTypeId] = mealTypeId
            preferences[selectedDietType] = dietType
            preferences[selectedDietTypeId] = dietTypeId
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }
        .map {
            val selectedMealType = it[selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = it[selectedMealTypeId] ?: 0
            val selectedDietType = it[selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = it[selectedDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int,
)