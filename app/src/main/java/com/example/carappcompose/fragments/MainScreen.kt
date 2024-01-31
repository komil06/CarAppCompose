package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
//   Scaffold (
//       topBar = {
//           TopAppBar(navigationIcon = {
//               IconButton(onClick = {}, modifier = Modifier.width(30.dp).height(30.dp)) {
//                   Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
//               }
//               Text(text = "CarStore", modifier = Modifier.padding(30.dp))
//               IconButton(onClick = {}) {
//                   Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
//               }
//           },
//               title = {
//                   Text(text = "")
//               })
//       }
//   ){
//
//   }


    Scaffold(

        topBar = {
           TopAppBar( modifier = Modifier.fillMaxWidth(), navigationIcon = {
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
       }, bottomBar = {
            BottomAppBar(modifier = Modifier.height(70.dp).padding(start = 50.dp)) {


//                Row(modifier = Modifier.fillMaxWidth().height(80.dp)){





//                IconButton(modifier = Modifier.fillMaxSize(), onClick = { navController.navigate("Settings") }) {
//                    Icon(
//                        imageVector = Icons.Default.Settings,
//                        contentDescription = "Details Icon",
//                        modifier = Modifier.width(50.dp).height(50.dp)
//                    )
//                    Icon(
//                        imageVector = Icons.Default.Settings,
//                        contentDescription = "Details Icon",
//                        modifier = Modifier.width(50.dp).height(50.dp)
//                    )
//                    Icon(
//                        imageVector = Icons.Default.Settings,
//                        contentDescription = "Details Icon",
//                        modifier = Modifier.width(50.dp).height(50.dp)
//                    )
//
//                }

            }
        }





    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
//            items() { item ->
//            }
        }



    }
}