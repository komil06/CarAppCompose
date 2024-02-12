package com.example.carappcompose.navigation

sealed class Screens(val route:String){
     object Splash:Screens("Splash")
     object SignIn:Screens("SignIn")
     object SignUp:Screens("SignUp")


     object Filter:Screens("Filter")
     object Main:Screens("Main")
     object Details:Screens("Details")

     object Wishlist:Screens("Wishlist")

     object Personal:Screens("Personal")

     object Selling:Screens("Selling")

}