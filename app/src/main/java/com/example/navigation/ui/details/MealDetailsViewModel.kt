package com.example.navigation.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.navigation.data.model.MealsRepository
import com.example.navigation.data.model.response.MealsCategoriesResponse

class MealDetailsViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private val repository: MealsRepository = MealsRepository.getInstance()
    val mealState = mutableStateOf<MealsCategoriesResponse.MealResponse?>(null)
    init {
        val mealId = savedStateHandle.get<String>("mealsCategoryId")?: ""
        mealState.value = repository.getMeal(mealId)
    }
}