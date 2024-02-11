package com.example.carappcompose.navigation

sealed class Screens(val route:String){
     object Splash:Screens("Splash")
     object SignIn:Screens("SignIn")
     object RItem:Screens("RItem")
     object SignUp:Screens("SignUp")

     object Main:Screens("Main")
     object Details:Screens("Details")

     object Wishlist:Screens("Wishlist")

     object Personal:Screens("Personal")

     object Selling:Screens("Selling")

}