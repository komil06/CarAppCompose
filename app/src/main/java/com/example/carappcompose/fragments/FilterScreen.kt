package com.example.carappcompose.fragments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.Item
import com.example.carappcompose.RecommendItem
import com.example.carappcompose.ui.theme.primaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController) {
    var cars by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    CarData.GetCars { list ->
        cars = list
    }
    var sliderPosition by remember{ mutableFloatStateOf(0f)}
    Column(modifier = Modifier.padding(10.dp)) {
        val pagerState = rememberPagerState(
            pageCount = { 3 }
        )
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = TabRowDefaults.containerColor,
            contentColor = TabRowDefaults.contentColor,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    height = 2.dp,
                    color = primaryColor
                )
            }) {
            Tab(
                selected = pagerState.currentPage == 0,
                text = {
                    Text(
                        text = "All",
                        color = Color(255, 87, 34, 255),
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = {
                    coroutineScope.launch { pagerState.scrollToPage(0) }
                }
            )
            Tab(
                selected = pagerState.currentPage == 1,
                text = {
                    Text(
                        text = "New",
                        color = Color(255, 87, 34, 255),
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = { coroutineScope.launch { pagerState.scrollToPage(1) } }
            )
            Tab(
                selected = pagerState.currentPage == 2,
                text = {
                    Text(
                        text = "Used",
                        color = Color(255, 87, 34, 255),
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = { coroutineScope.launch { pagerState.scrollToPage(2) } }
            )
        }
        HorizontalPager(state = pagerState, userScrollEnabled = true) {
            var name by remember { mutableStateOf(TextFieldValue("")) }
            var brand by remember {mutableStateOf(TextFieldValue("")) }
            if (pagerState.currentPage == 0) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp, 0.dp, 50.dp, 0.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    TextField(value = name.text, onValueChange = {name.text}, label = { Text("Enter model name") })
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(value = brand.text, onValueChange = {brand.text}, label = { Text("Enter brand name") })
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Price Range", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        colors = SliderDefaults.colors(
                            thumbColor = Color(255, 87, 34, 255),
                            activeTrackColor = Color(255, 150, 90, 255)
                        ),
                        valueRange = 0f..1000f
                    )
                    Text(
                        text = "${sliderPosition.toInt()}/1000",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), modifier = Modifier
                            .padding(bottom= 100.dp)
                    ) {
                        items(UserData.searchList.size) {
                             UserData.searchList
                        }
                    }

                    Spacer(modifier = Modifier.height(70.dp))
                    Button(
                        onClick = {UserData.searchCar(name.text)}, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(20.dp)
                            .height(55.dp)
                            .width(400.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(Color(255, 87, 34, 255))
                    ) {
                        Text(
                            text = "Filter",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

                if (pagerState.currentPage == 1) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp, 10.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "Here are new cars",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(255, 87, 34, 255)
                            )
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), modifier = Modifier
                                    .padding(bottom= 100.dp)
                            ) {
                                items(cars) { item ->
//
                                    RecommendItem(name = item, price = item, navController)
//
                                }
                            }
                        }
                    }
                    if (pagerState.currentPage == 2) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp, 10.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "Find cheaper cars",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(255, 87, 34, 255)
                            )
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), modifier = Modifier
                                    .padding(bottom= 100.dp)
                            ) {
                                items(cars) { item ->
//
                                    RecommendItem(name = item, price = item, navController)
//
                                }
                                }
                            }
                        }
                    }

            }
        }



  


