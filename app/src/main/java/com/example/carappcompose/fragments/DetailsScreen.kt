package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.R
import com.example.carappcompose.ui.theme.poppinsFamily
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(navController: NavController){
     Scaffold(
         topBar = {
             CenterAlignedTopAppBar(title = {},
                 navigationIcon = {
                     IconButton(onClick = { /* do something */ }) {
                         Icon(
                             imageVector = Icons.Filled.ArrowBack,
                             contentDescription = "Localized description",
                             modifier = Modifier
                                 .height(35.dp)
                                 .width(40.dp)
                         )
                     }
                 },
                 actions = {
                     IconButton(onClick = { /* do something */ }) {
                         Icon(
                             imageVector = Icons.Default.Share,
                             contentDescription = "Localized description",
                             modifier = Modifier
                                 .height(35.dp)
                                 .width(40.dp)
                         )
                     }
                 },

                 )
         }
     ) {}
    Spacer(modifier = Modifier.height(30.dp))
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), contentAlignment = Alignment.Center){
            Image(painter = painterResource(id = R.drawable.login_image), contentDescription = "Car name")

        }
        Row (modifier = Modifier.padding(0.dp,10.dp)){
            Text(text = "Car name", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(35.dp, 10.dp))
            Text(text = "Car rate", fontSize = 20.sp, color = Color.Magenta, modifier = Modifier.padding(28.dp,5.dp))
        }
        Text(text = "Car price", fontSize = 20.sp)
        Text(text = "Car description", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(180.dp))
        Button(
            modifier = Modifier
                .padding(20.dp)
                .height(55.dp)
                .width(400.dp),
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(255, 87, 34, 255))
        ){
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Buy Now",
                fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        }

    }





}

