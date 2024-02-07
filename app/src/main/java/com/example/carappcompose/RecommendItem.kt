package com.example.carappcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecommendItem(navController: NavController){
    Card(modifier = Modifier
        .height(400.dp)
        .width(300.dp)
    ){
       Image(painter = painterResource(id = R.drawable.login_image), contentDescription = null,
           modifier = Modifier
               .fillMaxWidth()
               .height(300.dp))
        Column(modifier = Modifier.height(100.dp)){
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Text(text = "CarName",fontWeight = FontWeight.Bold,fontSize = 20.sp)
                Image(painter = painterResource(id = R.drawable.fav_border), contentDescription = null)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "CarPrice")
        }

    }

}
