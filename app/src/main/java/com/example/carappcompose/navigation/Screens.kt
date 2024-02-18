package com.example.carappcompose.navigation


const val NAME_KEY = "name_key"

sealed class Screens(val route:String){
     object Splash:Screens("Splash")
     object SignIn:Screens("SignIn")
     object SignUp:Screens("SignUp")


     object Filter:Screens("Filter")
     object Main:Screens("Main")
//     object Details:Screens("Details")


     object Details : Screens("Details/{$NAME_KEY}") {
          fun getFullRoute(name: String): String {
               return "Details/$name"
          }
     }

     object Wishlist:Screens("Wishlist")

     object Personal:Screens("Personal")

     object Selling:Screens("Selling")

     object NewCar:Screens("NewCar")

}