package com.example.carappcompose.fragments

import android.annotation.SuppressLint
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
import com.example.carappcompose.ui.theme.primaryColor
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(name:String, price:String,navController: NavController) {

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
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                Spacer(modifier = Modifier.height(30.dp))




                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hyundai),
                            contentDescription = "Car name"
                        )



                    }

//
//                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
//                        Image(
//                            painter = painterResource(id = R.drawable.hyundai),
//                            contentDescription = "Car name",
//                            modifier = Modifier.width(110.dp) .border(
//                                BorderStroke(1.dp, primaryColor),
//                                shape = RoundedCornerShape(20.dp),
//
//                                )
//
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.hyundai),
//                            contentDescription = "Car name",
//                            modifier = Modifier.width(110.dp).border(
//                                BorderStroke(1.dp, primaryColor),
//                                shape = RoundedCornerShape(20.dp),
//
//                                )
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.hyundai),
//                            contentDescription = "Car name",
//                            modifier = Modifier.width(110.dp).border(
//                                BorderStroke(1.dp, primaryColor),
//                                shape = RoundedCornerShape(20.dp),
//
//                                )
//                        )
//
//                    }
                    Text(text = "Car name: $name", fontFamily = poppinsFamily, fontSize = 22.sp, fontWeight = FontWeight.Bold)
//
                    Text(text = "Car price: $price", fontSize = 18.sp, fontFamily = poppinsFamily, modifier = Modifier.padding(0.dp),
                        color = Color(168,175,185),fontWeight = FontWeight.Medium)
                    Text(text = "Condition: ", fontSize = 18.sp, fontFamily = poppinsFamily,modifier = Modifier.padding(0.dp),
                        color = Color(168,175,185),fontWeight = FontWeight.Medium
                        )
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam scelerisque ante sem, vel mattis erat ultrices vel. Suspendisse ligula enim, commodo quis nisi at, sodales varius sem.Aliquam sem nibh, porttitor ut imperdiet et, ultricies vitae lacus. Ut vestibulum non enim vitae sollicitudin.",
                        fontSize = 15.sp, fontFamily = poppinsFamily,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Medium,
                        color = Color(168,175,185)
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

