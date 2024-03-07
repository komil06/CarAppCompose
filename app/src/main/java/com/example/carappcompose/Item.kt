package com.example.carappcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.carappcompose.navigation.Screens
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor

@Composable
fun Item(name: String, price:String, condition: String,description: String, imgUrl:String, year:String,navController: NavController){


    Card(modifier = Modifier.padding(5.dp).width(150.dp).height(150.dp)
        .clickable {

            navController.navigate(route = Screens.Details.getFullRoute(name = name, price = price,condition = condition,description = description, year = year))

        }
    ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,),

        ){

        Column(modifier = Modifier.fillMaxSize()){
            Image(
                painter = rememberAsyncImagePainter(imgUrl),


                contentDescription = "gfg image",

                modifier = Modifier
                    .size(150.dp)
                    .border(
                        BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(12.dp),),


                contentScale = ContentScale.Crop,

                )

        }

    }
}
