package com.example.carappcompose.navigation

sealed class Screens(val route: String){
    object Splash:Screens("Splash")
    object SignUp:Screens("SignUp")
    object SignIn:Screens("SignIn")
    object Main:Screens("Main")
    object Details:Screens("Details")
    object Filter:Screens("Filter")
    object RItem:Screens("RItem")
}
