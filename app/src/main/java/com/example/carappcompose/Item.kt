package com.example.carappcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.ui.theme.poppinsFamily

@Composable
fun Item(navController: NavController){


    Card(modifier = Modifier.padding(5.dp).width(175.dp).height(200.dp),){

        Column(modifier = Modifier){
            Image(
                painter = painterResource(id = R.drawable.hyundai),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),


                )

            Spacer(modifier = Modifier.height(10.dp))


            Text(text = "Car name",modifier = Modifier.fillMaxWidth(), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "$50,000",modifier = Modifier.fillMaxWidth(), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium,textAlign = TextAlign.Center, color = Color(255,165,0))


        }

    }
}
