package com.example.carappcompose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.navigation.Screens
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor

@Composable
fun RecommendItem(name: String, price:String, condition: String,description: String,imgUrl:String, navController: NavController){
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(5.dp)
        .width(175.dp)
        .fillMaxHeight()

        .border(
            BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(12.dp),)
        .clickable {
            navController.navigate(route = Screens.Details.getFullRoute(name = name, price = price,condition = condition,description = description))

        },   colors = CardDefaults.cardColors(
        containerColor = Color.White,
    )){
        var isClicked by remember {
            mutableStateOf(false)
        }

        UserData.isFavourite(UserData.getUserSaved(context), name) {
            isClicked = it
            Log.d("TAG", "$name $it")

        }


        IconButton(
            onClick = {
                UserData.FavouritesCreate(UserData.getUserSaved(context), name)
                isClicked = !isClicked
        },
            Modifier.align(Alignment.End)
        ) {

            Image(
                painter = if (isClicked) {

                    painterResource(id = R.drawable.baseline_favorite_24)

                } else {
                    painterResource(id = R.drawable.baseline_favorite_border_24)
                },
                contentDescription = null,

            )

        }
Column(modifier = Modifier){




    Image(
        painter = rememberAsyncImagePainter(imgUrl),


        contentDescription = "gfg image",

        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ,

        contentScale = ContentScale.Crop,

        )
    Spacer(modifier = Modifier.height(5.dp))


    Text(text = "$name".uppercase(),modifier = Modifier.fillMaxWidth(), fontFamily = poppinsFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

    Spacer(modifier = Modifier.height(5.dp))
    Text(text = "$price".uppercase() + " $",modifier = Modifier.fillMaxWidth(), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium,textAlign = TextAlign.Center, color = Color(255,165,0))
    Spacer(modifier = Modifier.height(3.dp))


}

    }
}
