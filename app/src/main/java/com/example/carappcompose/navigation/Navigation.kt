package com.example.carappcompose.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carappcompose.fragments.SignInScreen
import com.example.carappcompose.fragments.SplashScreen

@Composable
fun Navigation(navController:NavHostController){
NavHost(navController = navController, startDestination = Screens.Splash.route){
    composable(route = Screens.Splash.route) {
       SplashScreen(navController = navController)
    }
    composable(route = Screens.SignIn.route){
        SignInScreen(navController = navController)
    }
}
}