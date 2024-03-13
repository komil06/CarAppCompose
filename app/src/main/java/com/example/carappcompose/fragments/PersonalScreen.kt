package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedButton
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
import com.example.carappcompose.navigation.NavigationItem
import com.example.carappcompose.firebaseUI
import com.example.carappcompose.navigation.Screens
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false)}
    var cars2 by remember {
        mutableStateOf<List<CarClass>>(emptyList())
    }
    UserData.FavouriteGet(UserData.getUserSaved(context)) { lst ->
        CarData.FavouritesFilter(lst) {
            cars2 = it
//            Log.d("TAGi", cars.toString())
        }
    }

    val carsLength: Int = cars2.size
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
            badgeCount = carsLength

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
                                "Profile",
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

                    NavigationBar(modifier = Modifier.zIndex(3f).padding(bottom = 20.dp,
                        start = 25.dp, end = 25.dp, top = 20.dp
                    )
                        .clip(RoundedCornerShape(25.dp)),
//                        .background(Color.White)
                            containerColor = primaryColor




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
                                        modifier = Modifier.padding(top = 25.dp)
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





















// Shu yerga yoziladi

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp), verticalArrangement = Arrangement.Center) {

                Column(modifier = Modifier.fillMaxWidth().padding(top = 20.dp).clickable { showDialog = true }, horizontalAlignment = Alignment.CenterHorizontally,

                    ) {

                    firebaseUI(LocalContext.current)

                }




                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top =15.dp).clickable { }, horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {


                    OutlinedButton(
                        onClick = {
//                            navController.navigate("Selling")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                            .clip(CircleShape)
                            .border(0.5.dp, Color.Gray),






                        ){
                        Text(
                            text = "My Username: ",

                            fontFamily = poppinsFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = UserData.getUserSaved(context),


                            fontFamily = poppinsFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis
                        )
                      
                    }




                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 15.dp).clickable { }, horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {


                    OutlinedButton(
                        onClick = {
                            navController.navigate("Selling")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                            .clip(CircleShape)
                            .border(0.5.dp, Color.Gray),






                    ){
                        Text(
                            text = "My cars",
                            fontFamily = poppinsFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Localized description",
                            modifier = Modifier
                                .padding(start = 150.dp, end = 10.dp)
                        )
                    }




                    }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 15.dp).clickable { }, horizontalArrangement = Arrangement.SpaceEvenly,

                    ) {


                    OutlinedButton(
                        onClick = {
                            navController.navigate("ChangePassword")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                            .clip(CircleShape)
                            .border(0.5.dp, Color.Gray),






                        ){
                        Text(
                            text = "Change Password",
                            fontFamily = poppinsFamily,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor,
                            overflow = TextOverflow.Ellipsis
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Localized description",
                            modifier = Modifier
                                .padding(start = 45.dp, end = 10.dp)
                        )
                    }




                }




//                Row(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 10.dp, end = 10.dp, top = 15.dp).clickable { }, horizontalArrangement = Arrangement.SpaceEvenly,
//
//                    ) {
//
//
//                    OutlinedButton(
//                        onClick = {
//                            navController.navigate("ChangeImage")
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 20.dp, end = 20.dp)
//                            .clip(CircleShape)
//                            .border(0.5.dp, Color.Gray),
//
//
//
//
//
//
//                        ){
//                        Text(
//                            text = "Change Image",
//                            fontFamily = poppinsFamily,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = primaryColor,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        Icon(
//                            imageVector = Icons.Filled.KeyboardArrowRight,
//                            contentDescription = "Localized description",
//                            modifier = Modifier
//                                .padding(start = 45.dp, end = 10.dp)
//                        )
//                    }
//
//
//
//
//                }




                }

                }

            }








        }




