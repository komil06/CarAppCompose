package com.example.carappcompose.navigation



const val NAME_KEY = "name_key"
const val PRICE_KEY = "price_key"
const val CONDITION_KEY = "condition_key"
const val DESCRIPTION_KEY = "description_key"
const val YEAR_KEY = "year_key"

const val MILE_KEY = "mile_key"

sealed class Screens(val route:String){
     object Splash:Screens("Splash")
     object SignIn:Screens("SignIn")
     object SignUp:Screens("SignUp")


     object Filter:Screens("Filter")
     object Main:Screens("Main")
//     object Details:Screens("Details")

object OnBoarding:Screens("OnBoarding")

     object Details : Screens("Details/{$NAME_KEY}/{$PRICE_KEY}/{$CONDITION_KEY}/{$DESCRIPTION_KEY}/{$YEAR_KEY}/{$MILE_KEY}") {
          fun getFullRoute(name: String, price:String, condition:String, description:String, year:String,mile:String ): String {
               return "Details/$name/$price/$condition/$description/$year/$mile"
          }
     }

     object Wishlist:Screens("Wishlist")

     object Profile:Screens("Profile")

     object Selling:Screens("Selling")

     object NewCar:Screens("NewCar")

     object ChangePassword:Screens("ChangePassword")
//     object ChangeImage:Screens("ChangeImage")

}