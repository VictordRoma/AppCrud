package com.example.ecrimeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.ecrimeapp.data.AppContainer
import com.example.ecrimeapp.ui.navigation.CrimeNavGraph
import com.example.ecrimeapp.ui.theme.EcrimeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcrimeAppTheme{
                val appContainer = AppContainer(applicationContext)
                val bookRepository = appContainer.criminosoRepository
                val navController = rememberNavController()
                CrimeNavGraph(navController = navController, criminosoRepository = bookRepository)            }
        }
    }
}
