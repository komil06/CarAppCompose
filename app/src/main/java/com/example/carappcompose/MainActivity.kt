package com.example.carappcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carappcompose.fragments.AddNewCarScreen
import com.example.carappcompose.fragments.ChangePassword
import com.example.carappcompose.fragments.DetailsScreen
import com.example.carappcompose.fragments.MainScreen
import com.example.carappcompose.fragments.OnboardingScreen
import com.example.carappcompose.fragments.ProfileScreen
import com.example.carappcompose.fragments.SeeAllScreen
import com.example.carappcompose.fragments.SellingCarScreen
import com.example.carappcompose.fragments.SignInScreen
import com.example.carappcompose.fragments.SignUpScreen
import com.example.carappcompose.fragments.SplashScreen
import com.example.carappcompose.fragments.WishlistScreen
import com.example.carappcompose.navigation.CONDITION_KEY
import com.example.carappcompose.navigation.DESCRIPTION_KEY
import com.example.carappcompose.navigation.MILE_KEY
import com.example.carappcompose.navigation.NAME_KEY
import com.example.carappcompose.navigation.PHONE_KEY
import com.example.carappcompose.navigation.PRICE_KEY
import com.example.carappcompose.navigation.Screens
import com.example.carappcompose.navigation.TGUSERNAME_KEY
import com.example.carappcompose.navigation.YEAR_KEY
import com.example.carappcompose.ui.theme.CarAppComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           CarAppComposeTheme {
               val navController = rememberNavController()
               NavHost(navController = navController, startDestination = Screens.Splash.route){
                   composable(route = Screens.Splash.route) {
                       SplashScreen(navController = navController)
                   }
                   composable(route = Screens.OnBoarding.route) {
                       OnboardingScreen(navController = navController, this@MainActivity)
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
                   composable(route = Screens.Details.route, arguments = listOf(
                       navArgument(NAME_KEY) {
                       type = NavType.StringType
                   },

                       navArgument(PRICE_KEY) {
                           type = NavType.StringType
                       },
                       navArgument(CONDITION_KEY) {
                           type = NavType.StringType
                       },
                       navArgument(DESCRIPTION_KEY) {
                           type = NavType.StringType
                       },

                       navArgument(YEAR_KEY) {
                           type = NavType.StringType
                       },
                       navArgument(MILE_KEY) {
                           type = NavType.StringType
                       },
                       navArgument(TGUSERNAME_KEY) {
                           type = NavType.StringType
                       },
                       navArgument(PHONE_KEY) {
                           type = NavType.StringType
                       },
                       ),
                       ) { navBackStackEntry ->
                       val name = navBackStackEntry.arguments?.getString(NAME_KEY)
                       val price = navBackStackEntry.arguments?.getString(PRICE_KEY)
                       val condition = navBackStackEntry.arguments?.getString(CONDITION_KEY)
                       val description = navBackStackEntry.arguments?.getString(DESCRIPTION_KEY)
                       val year = navBackStackEntry.arguments?.getString(YEAR_KEY)
                       val mile = navBackStackEntry.arguments?.getString(MILE_KEY)
                       val tg_username = navBackStackEntry.arguments?.getString(TGUSERNAME_KEY)
                       val phone = navBackStackEntry.arguments?.getString(PHONE_KEY)
                       if (name != null && price!= null && condition!= null && description!= null && year!= null && mile!= null && tg_username!= null && phone!= null ) {
                           DetailsScreen(name = name,price= price, condition = condition,description = description,year = year, mile = mile, tg_username = tg_username, phone = phone, navController)
                       }
                   }
                   composable(route = Screens.Wishlist.route) {
                       WishlistScreen(navController = navController)
                   }
                   composable(route = Screens.Profile.route) {
                       ProfileScreen(navController = navController)
                   }
                   composable(route = Screens.Selling.route) {
                       SellingCarScreen(navController = navController)
                   }

                   composable(route = Screens.ChangePassword.route){
                       ChangePassword(navController = navController)
                   }
                   composable(route = Screens.NewCar.route){
                       AddNewCarScreen(navController = navController)
                   }

                   composable(route = Screens.SeeAllScreen.route){
                       SeeAllScreen(navController = navController)
                   }
               }
           }
        }
    }
}
