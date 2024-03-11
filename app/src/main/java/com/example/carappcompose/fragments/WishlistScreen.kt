package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.Database.UserClass
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.NavigationItem
import com.example.carappcompose.R
import com.example.carappcompose.firebaseUI
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavController){
    val context = LocalContext.current

   val wished by remember {mutableStateOf<List<String>>(emptyList()) }
//    UserData.FavouritesGet{
//
//    }
    var cars by remember { mutableStateOf<List<CarClass>>(emptyList()) }
    CarData.GetCars { list ->
        cars = list
    }

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
        ),
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(1)
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
                        title = {
                            Text(
                                "Wishlist",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = primaryColor
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {   scope.launch {
                                drawerState.open()
                            } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(40.dp)
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* do something */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Notifications,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(40.dp)
                                )
                            }
                        },
                    )
                },
                bottomBar = {

                    NavigationBar(modifier = Modifier.zIndex(3f).padding(bottom = 20.dp,
                        start = 25.dp, end = 25.dp, top = 20.dp
                    )
                        .clip(RoundedCornerShape(25.dp))
//                        .background(Color.White)
                        .border(
                            BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(25.dp),),



                        ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navController.navigate("${item.title}")

                                },
                                label = {
                                    Text(text = item.title,
                                        fontFamily = poppinsFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(top = 20.dp)
                                    )
                                },
                                alwaysShowLabel = false,
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























//            LazyVerticalGrid(
//                columns = GridCells.Fixed(2), modifier = Modifier
//                    .padding(top = 50.dp, bottom = 100.dp)
//
//            ) {
//                items(cars) { item ->
//
//                }}



            MyLazyColumn()




        }



    }


}


@Composable
fun MyLazyColumn() {

    var cars by remember { mutableStateOf<List<CarClass>>(emptyList()) }
    val context = LocalContext.current
//    CarData.FavouritesFilter(UserData.getUserSaved(context), ) { list ->
//        cars = list
//    }
    if (cars.isEmpty()) {
        LazyColumn {
            items(cars) { item ->
                // Your item UI here
            }
        }
    } else {
     EmptyIcon()
    }
}

@Composable
fun EmptyIcon() {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/cb9658f7-56e1-418c-b9f9-c94d3af5d5b0/CXDVREveWd.lottie"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.width(200.dp).height(200.dp))
        }
}