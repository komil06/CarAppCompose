package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.Item
import com.example.carappcompose.NavigationItem
import com.example.carappcompose.RecommendItem
import com.example.carappcompose.firebaseUI
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){

    var searchText by remember {
        mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current



    var cars by remember {
    mutableStateOf<List<CarClass>>(emptyList())
    }
//    var cars by remember {
//
//        mutableStateOf<CarClass<List<String>>>(emptyList())
//    }


//    var carList = mutableListOf<CarClass>()

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
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }

//        var images = UserData.getSavedImage(context,)
        ModalNavigationDrawer(

            drawerContent = {
                ModalDrawerSheet {

                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {


                    Column(modifier = Modifier.fillMaxWidth().padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally,

                        ) {

                        firebaseUI(LocalContext.current)

                    }

//                    if (UserData.imageUrlState != null) {
//                        Image(
//                            painter = rememberImagePainter(
//                                data = UserData.imageUrlState,
//                                builder = {
//                                    // You can customize image loading options here if needed
//                                }
//                            ),
//                            contentDescription = "User Image",
//                            modifier = Modifier
//                                .size(200.dp) // Adjust the size as needed
//                                .clip(MaterialTheme.shapes.medium)
//                        )
//                    }

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
//                            badge = {
//                                item.badgeCount?.let {
//                                    Text(text = item.badgeCount.toString())
//                                }
//                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .clickable {
                                }
                            ,


                        )
                    }

//                    Button(modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
//                        onClick = {
//                            UserData.UserSave(context, "")
//                            navController.navigate("SignIn")
//                        }) {
//                        Text(
//                            modifier= Modifier.clickable {
//                               },
//                            text = "Log out",
//                            fontFamily = poppinsFamily,
//                            fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
//                            color = Color.Black
//                            )
//                    }


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



















            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                OutlinedTextField(
                    value = searchText,
                    leadingIcon = {Icon(imageVector =Icons.Default.Search, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                    onValueChange = {searchText},
                    label = { Text("Search",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                    )
                )
                UserData.searchCar(searchText.toString())




//                SearchView(state = textState, placeHolder = "Search here...", modifier = modifier)

                IconButton(  onClick = {navController.navigate("Filter")}, modifier = Modifier.padding(top = 10.dp)){
                    Icon(imageVector = Icons.Default.List, contentDescription = null, modifier = Modifier
                        .width(50.dp)
                        .height(75.dp),
                    )
                }


            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp)){
                LazyRow(
                ) {
                    items(cars) { item ->
//
                        item.title?.let { item.price?.let { it1 -> item.brand?.let { it2 -> item.description?.let { it3 ->Item(name = it, price = it1,condition = it2, description = it3, navController) } } }
//
                        }
                    }
                }
            }




            Column(modifier = Modifier) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 320.dp, start = 15.dp, end = 15.dp), horizontalArrangement = Arrangement.SpaceBetween,){
                    Text(text = "Recommended", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "See all", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(168,175,185))

                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(bottom = 100.dp, start = 10.dp, end = 10.dp)
                ) {
                    items(cars) { item ->
//
                        item.title?.let { item.price?.let { it1 -> item.brand?.let { it2 -> item.description?.let { it3 ->RecommendItem(name = it, price = it1,condition = it2, description = it3, navController) } } }
//
                        }
                }
            }


        }
    }









}}



