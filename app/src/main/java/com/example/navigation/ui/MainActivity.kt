package com.example.navigation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.details.MealDetailsScreen
import com.example.navigation.ui.details.MealDetailsViewModel
import com.example.navigation.ui.meals.MealsCatteryScreen
import com.example.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    FoodDetails()
                }
            }
        }
    }
}

@Composable
fun FoodDetails(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "destination_meal_list"){
        composable(
            route = "destination_meal_list"){
            MealsCatteryScreen{ navugationMealsId ->
                navController.navigate("destination_food_Details/$navugationMealsId")
            }
        }
        composable(
            route = "destination_food_Details/{mealsCategoryId}",
            arguments = listOf(navArgument("mealsCategoryId"){
                type = NavType.StringType })){
            val viewModel: MealDetailsViewModel = viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }
}
