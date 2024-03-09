package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.NavigationItem
import com.example.carappcompose.RecommendItem
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllScreen(navController: NavController){


    var searchText = remember{
        mutableStateOf(TextFieldValue(""))
    }


    var cars by remember {
        mutableStateOf<List<CarClass>>(emptyList())
    }

    CarData.GetCars { list ->
        cars = list
    }


    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
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
            badgeCount = 5

        ),
        NavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
        ),
    )



    Scaffold(


        bottomBar = {

            NavigationBar(
                modifier = Modifier.zIndex(3f).padding(
                    bottom = 20.dp,
                    start = 25.dp, end = 25.dp, top = 20.dp
                )
                    .clip(RoundedCornerShape(25.dp))
//                        .background(Color.White)
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(25.dp),
                    ),


                ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate("${item.title}")

                        },
                        label = {
                            Text(
                                text = item.title,
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        },
                        alwaysShowLabel = false,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
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









    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        horizontalArrangement = Arrangement.Center
        ,
    ) {



    SearchView(
        state = searchText, placeHolder = "Search Here... ", modifier = Modifier
    )


}


    Column(modifier = Modifier.padding(top = 90.dp)) {
        val filteredCars = cars.filter {
            it.title?.contains(searchText.value.text, ignoreCase = true) == true

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = 100.dp, start = 10.dp, end = 10.dp)
        ) {
            items(items = filteredCars) { item ->
                item.title?.let {
                    item.price?.let { it1 ->
                        item.condition?.let { it2 ->
                            item.description?.let { it3 ->
                                item.imageUrl?.let { it4 ->
                                    item.year?.let { it5 ->
                                        item.mileage?.let { it6 ->
                                            RecommendItem(
                                                name = it,
                                                price = it1,
                                                condition = it2,
                                                description = it3,
                                                imgUrl = it4,
                                                year = it5,
                                                mile = it6,
                                                navController
                                            )

                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }


        }


    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>, placeHolder: String, modifier: Modifier) {

        TextField(
    value = state.value,

    onValueChange = {value ->

        state.value = value
    },


    modifier = Modifier.fillMaxWidth().padding(10.dp).border(1.dp, primaryColor, RoundedCornerShape(30.dp)).clip(RoundedCornerShape(30.dp))
        .background(Color.White)
            ,

    placeholder = {
        Text(text  = placeHolder,
            fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold
        )
    },

    maxLines =1,
    singleLine =true,
    textStyle = TextStyle(

        color = Color.Black, fontSize = 20.sp
    ),

            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )

)
}