package com.example.carappcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.ui.theme.poppinsFamily

@Composable
fun Item(navController: NavController){


    Card(modifier = Modifier.padding(5.dp).width(175.dp).height(150.dp)
        .clickable {
            navController.navigate("Details")
        }
        .border(
            BorderStroke(1.dp, Color.Gray),
            CircleShape
            )

        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,),

        ){

        Column(modifier = Modifier){
            Image(
                painter = painterResource(id = R.drawable.hyundai),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()


                ,




                )


        }

    }
}
