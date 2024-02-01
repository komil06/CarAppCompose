package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){
    var presses by remember { mutableIntStateOf(0) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "CarStore",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = primaryColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                            modifier = Modifier.height(35.dp).width(40.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Localized description",
                            modifier = Modifier.height(35.dp).width(40.dp)
                        )
                    }
                },
//                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(bottom = 40.dp, start = 20.dp, end = 20.dp)
                    .clip(
                        RoundedCornerShape(30.dp)
                    ).fillMaxWidth().height(65.dp),


                    containerColor = Color.White






            ) {



                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                    IconButton(onClick = {}){
                    Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.width(40.dp).height(35.dp),
                    )
                    }
                    IconButton(onClick = {}){
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, modifier = Modifier.width(40.dp).height(35.dp),
                        )
                    }
                    IconButton(onClick = {}){
                        Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.width(40.dp).height(35  .dp),
                        )
                    }



                }
            }
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { presses++ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }
//        }
    )
    {

    }





    Row(modifier = Modifier.fillMaxWidth().padding(top = 70.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
        ){

        OutlinedTextField(
            value = searchText,
            leadingIcon = {Icon(imageVector =Icons.Default.Search, contentDescription = null, modifier = Modifier.padding(8.dp)) },

            onValueChange = { searchText = it },
            label = { Text("Search",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primaryColor,
            )
        )

        IconButton(onClick = {}, modifier = Modifier.padding(top = 10.dp)){
            Icon(imageVector = Icons.Default.List, contentDescription = null, modifier = Modifier.width(50.dp).height(75.dp),
            )
        }
        LazyRow (){

        }

    }



    Row(modifier = Modifier.fillMaxWidth().padding(top = 300.dp, start = 15.dp, end = 15.dp), horizontalArrangement = Arrangement.SpaceBetween,){
        Text(text = "Recommended", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "See all", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(168,175,185))

    }

    LazyRow (){

    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainPreview(){
//    MainScreen()
//}
