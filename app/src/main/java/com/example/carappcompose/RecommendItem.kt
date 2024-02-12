package com.example.carappcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.ui.theme.poppinsFamily

@Composable
fun RecommendItem(navController: NavController){


    Card(modifier = Modifier.padding(5.dp).width(175.dp).height(320.dp).clickable { navController.navigate("Details")},   colors = CardDefaults.cardColors(
        containerColor = Color.White,
    ),){
        var isClicked by remember {
            mutableStateOf(false)
        }

        val painterResource: Painter = if (isClicked) {
            painterResource(id = R.drawable.baseline_favorite_24)
        } else {
            painterResource(id = R.drawable.baseline_favorite_border_24)
        }
        IconButton(
            onClick = { isClicked = !isClicked },
            Modifier.align(Alignment.End)
        ) {

            Image(
                painter = painterResource,
                contentDescription = null,

            )

        }
Column(modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.hyundai),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp))
            .height(180.dp)
        ,




    )

    Spacer(modifier = Modifier.height(10.dp))


    Text(text = "Car name",modifier = Modifier.fillMaxWidth(), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "$50,000",modifier = Modifier.fillMaxWidth(), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium,textAlign = TextAlign.Center, color = Color(255,165,0))


}

    }
}
