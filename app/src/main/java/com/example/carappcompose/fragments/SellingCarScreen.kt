package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.Items.MyCarItem
import com.example.carappcompose.Items.RecommendItem
import com.example.carappcompose.effects.LeadingRowItem
import com.example.carappcompose.navigation.NavigationItem
import com.example.carappcompose.firebaseUI
import com.example.carappcompose.navigation.Screens
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import com.example.carappcompose.ui.theme.secondaryColor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellingCarScreen(navController: NavController){


    val context = LocalContext.current
    val items = listOf(
        NavigationItem(
            title = "Main",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "WishList",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,

            ),
        NavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
//            badgeCount = 45
        ),
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(2)
        }
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                        Column(modifier = Modifier.fillMaxWidth().padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally,

                            ) {

                            firebaseUI(LocalContext.current)

                        }
                        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = "Welcome to CarStore", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = Color(168,175,185))
                        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = UserData.getUserSaved(context), fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = primaryColor)

                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title,
                                    fontFamily = poppinsFamily,
                                    fontSize = 15.sp, fontWeight = FontWeight.SemiBold,

                                    )
                            },
                            selected = index == selectedItemIndex,
                            onClick = {

                                selectedItemIndex = index
                                navController.navigate("${item.title}")
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .clickable {
                                }
                            ,)
                    }
                    Button(modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        onClick = {
                            UserData.UserSave(context, "")
                            navController.navigate("SignIn")
                        }) {
                        Text(text = "Log out", fontSize = 20.sp,
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                }

            },
            drawerState = drawerState


        ) {
            Scaffold(

                topBar = {
                    CenterAlignedTopAppBar(

                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                        title = {
                            Text(
                                "Sell Car",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = primaryColor
                            )
                        },
                        navigationIcon = {
                            IconButton(modifier = Modifier
                                .clip(shape = RoundedCornerShape(25))
                                .background(Color.Black)
                                ,
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(40.dp)
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {

                                navController.navigate(Screens.SeeAllScreen.route)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search Icon",
                                    tint = Color.Black,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        },
                    )

                },
                bottomBar = {

                    NavigationBar(modifier = Modifier
                        .zIndex(3f)
                        .padding(
                            bottom = 20.dp,
                            start = 25.dp, end = 25.dp, top = 20.dp
                        )
                        .clip(RoundedCornerShape(25.dp))
                        .border(
                            BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(25.dp),
                        )


                        ,
                        containerColor = secondaryColor




                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate("${item.title}")

                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if(item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title,
                                            modifier = Modifier.size(35.dp),
                                            tint = primaryColor

                                        )
                                    }
                                }
                            )
                        }
                    }
                },
                )
            {

            }























            fun GetMyCars(callback: (List<CarClass>) -> Unit) {
                CarData.cars.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val cars = mutableListOf<CarClass>()
                        dataSnapshot.children.forEach {
                            val car = it.getValue(CarClass::class.java)
                            if (car!!.user ==  UserData.getUserSaved(context)) {
                                cars.add(car)
                            }
                        }
                        callback(cars)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        callback(emptyList())
                    }
                })
            }

            var cars by remember { mutableStateOf<List<CarClass>>(emptyList()) }
            CarData.GetCars { list ->
                cars = list
            }
            var mycars by remember { mutableStateOf<List<CarClass>>(emptyList()) }
            GetMyCars { list ->
                mycars = list
            }



            LazyColumn(modifier = Modifier.padding(top = 65.dp, bottom = 130.dp))
            {

                item {
                    LeadingRowItem(navController)
                }
                items(mycars) { item ->
                    item.title?.let {
                        item.price?.let { it1 ->
                            item.condition?.let { it2 ->
                                item.description?.let { it3 ->
                                    item.imageUrl?.let { it4 ->
                                        item.year?.let { it5 ->
                                            item.mileage?.let { it6 ->

                                                item.userTelegram?.let { it7 ->

                                                    item.phonenumber?.let { it8 ->
                                                        MyCarItem(
                                                            name = it,
                                                            price = it1,
                                                            condition = it2,
                                                            description = it3,
                                                            imgUrl = it4,
                                                            year = it5,
                                                            mile = it6,
                                                            tg_username = it7,
                                                            phonenumber = it8,
                                                            navController
                                                        )

                                                    }
                                                }
                                            }

                                        }

                                    }
                                }
                            }
//
                        }
                    }
                }

           }
//           Column(modifier = Modifier.fillMaxSize().padding(bottom = 105.dp, end = 30.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom,){
//               FloatingActionButton(
//                   onClick = { navController.navigate("NewCar") },
//               ) {
//                   Icon(Icons.Filled.Add, "Floating action button.")
//               }
//
//
//           }


        }



    }
}