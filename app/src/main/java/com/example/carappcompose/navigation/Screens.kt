package com.example.carappcompose.navigation

sealed class Screens(val route:String){
     object Splash:Screens("Splash")
     object SignIn:Screens("SignIn")

     object SignUp:Screens("SignUp")

     object Main:Screens("Main")
     object Details:Screens("Details")
}