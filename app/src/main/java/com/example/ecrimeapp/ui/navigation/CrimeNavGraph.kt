package com.example.ecrimeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecrimeapp.ui.SongViewModelFactory
import com.example.ecrimeapp.ui.HomeScreen
import com.example.ecrimeapp.data.CriminosoRepository
import com.example.ecrimeapp.ui.CrimeViewModel
import com.example.ecrimeapp.ui.CrimeScreen
import com.example.ecrimeapp.ui.SplashScreen

@Composable
fun CrimeNavGraph(navController: NavHostController, criminosoRepository: CriminosoRepository) {
    val viewModel: CrimeViewModel = viewModel(factory = SongViewModelFactory(criminosoRepository))

    NavHost(navController, startDestination = "splashScreen") {
        composable("splashScreen") { SplashScreen(navController) }
        composable("homeScreen") { HomeScreen(navController) }
        composable("crimeScreen") { CrimeScreen(viewModel) }
    }
}
