package com.example.navigation.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.navigation.data.model.MealsRepository
import com.example.navigation.data.model.response.MealsCategoriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoryViewModel(
    private val repository: MealsRepository = MealsRepository.getInstance()
) : ViewModel() {

    private val mealsJob = Job()

    init {
        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        scope.launch {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<MealsCategoriesResponse.MealResponse>> =
        mutableStateOf(emptyList())

    private suspend fun getMeals(): List<MealsCategoriesResponse.MealResponse> {
        return repository.getMeals().categories
    }

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
}