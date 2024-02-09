package com.example.carappcompose.navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carappcompose.RecommendItem
import com.example.carappcompose.fragments.DetailsScreen
import com.example.carappcompose.fragments.FilterScreen
import com.example.carappcompose.fragments.MainScreen
import com.example.carappcompose.fragments.PersonalScreen
import com.example.carappcompose.fragments.SignInScreen
import com.example.carappcompose.fragments.SignUpScreen
import com.example.carappcompose.fragments.SplashScreen
import com.example.carappcompose.fragments.WishlistScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController:NavHostController){
NavHost(navController = navController, startDestination = Screens.Splash.route){
    composable(route = Screens.Splash.route) {
       SplashScreen(navController = navController)
    }
    composable(route = Screens.SignIn.route){
        SignInScreen(navController = navController)
    }
    composable(route = Screens.SignUp.route){
        SignUpScreen(navController = navController)
    }

    composable(route = Screens.Main.route){
        MainScreen(navController = navController)
    }
    composable(route = Screens.Details.route) {
        DetailsScreen(navController = navController)
    }
    composable(route = Screens.RItem.route){
        RecommendItem(navController = navController)
    }
    composable(route = Screens.Filter.route){
        FilterScreen(navController = navController)
    }
    composable(route = Screens.Personal.route){
        PersonalScreen(navController = navController)
    }

    composable(route = Screens.Wishlist.route){
        WishlistScreen(navController = navController)
    }



}
}