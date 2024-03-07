package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.R
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(name:String, price:String,condition:String, description:String,year:String,mile:String, navController: NavController) {
    var img by remember { mutableStateOf("") }
    CarData.GetCarImage(name) {
        img = it
        Log.d("tag", img)
    }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("Main") }) {
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
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(start = 15.dp, end = 15.dp)) {

                Spacer(modifier = Modifier.height(30.dp))




                    Image(
                        painter = rememberAsyncImagePainter(img),


                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)

                          ,

                        contentScale = ContentScale.Crop,

                        )



                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, top = 20.dp)
                        , verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {


                    Text(text = "$year $name",
                        modifier = Modifier.border(
                            BorderStroke(1.dp, primaryColor),
                            shape = RoundedCornerShape(12.dp),
                        )
                            .padding(10.dp).fillMaxWidth(),

                        fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                    )

                    Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Text(text = "Narxi: $price $",
                            modifier = Modifier.border(
                                BorderStroke(1.dp, primaryColor),
                                shape = RoundedCornerShape(12.dp),
                            )
                                .padding(10.dp).weight(0.5f),
                            fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold

                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "Holati: $condition ",
                            modifier = Modifier.border(
                                BorderStroke(1.dp, primaryColor),
                                shape = RoundedCornerShape(12.dp),
                            )
                                .padding(10.dp).weight(0.5f),
                            fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold

                        )

                    }

                    Text(text = "Yurgan: $mile km",
                        modifier = Modifier.border(
                            BorderStroke(1.dp, primaryColor),
                            shape = RoundedCornerShape(12.dp),
                        )
                            .padding(10.dp).fillMaxWidth(),
                        fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold

                    )
                    Text(text = "Qo'shimcha: $description",
                        modifier = Modifier.border(
                            BorderStroke(1.dp, primaryColor),
                            shape = RoundedCornerShape(12.dp),
                        )
                            .padding(10.dp).fillMaxWidth(),
                        fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold

                    )





                    Button(
                        modifier = Modifier
                            .padding(20.dp)
                            .height(55.dp)
                            .width(400.dp),
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(Color(255, 87, 34, 255))
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                            text = "Buy Now",
                            fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                        )

                    }

                }
        }


    }

}

