package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
   Scaffold (
       topBar = {
           TopAppBar(navigationIcon = {
               IconButton(onClick = {}, modifier = Modifier.width(30.dp).height(30.dp)) {
                   Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
               }
               Text(text = "CarStore", modifier = Modifier.padding(30.dp))
               IconButton(onClick = {}) {
                   Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
               }
           },
               title = {
                   Text(text = "")
               })
       }
   ){

   }
}


