package com.example.navigation.data.model

import com.example.navigation.data.api.MealsWebService
import com.example.navigation.data.model.response.MealsCategoriesResponse

class MealsRepository(private val mealsWebService: MealsWebService = MealsWebService()) {

    private var mealsCache = listOf<MealsCategoriesResponse.MealResponse?>()
    suspend fun getMeals(): MealsCategoriesResponse {
        val response = mealsWebService.getMeals()
        mealsCache = response.categories
        return response
    }

    fun getMeal(id:String):MealsCategoriesResponse.MealResponse?{
        return mealsCache.firstOrNull {
            it?.id == id
        }
    }

    companion object{
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() =  instance?: synchronized(this){
            instance?: MealsRepository().also {
                instance = it
            }
        }
    }
}