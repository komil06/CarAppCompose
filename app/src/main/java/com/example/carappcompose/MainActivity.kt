package com.example.carappcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.carappcompose.navigation.Navigation
import com.example.carappcompose.ui.theme.CarAppComposeTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           CarAppComposeTheme {
               val navController = rememberNavController()
               Navigation(navController)
           }
        }
    }
}
