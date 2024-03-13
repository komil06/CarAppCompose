package com.example.carappcompose

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.navigation.Screens
import com.example.carappcompose.ui.theme.poppinsFamily

@Composable
fun AnimatedShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)


    )
    val transition = rememberInfiniteTransition(label = "")
    val alpha  = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1300,
                easing = FastOutSlowInEasing,

                ),
            repeatMode = RepeatMode.Restart
        ),
        label = "",
    ).value


    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end  = Offset(x = alpha, y =alpha)
    )

//    background(color = Color(0XFFC3C3C3).copy(alpha = alpha))

    ShimmerCard2(brush = brush)

}

@Composable
fun ShimmerCard2(brush: Brush){

 Row(modifier = Modifier.fillMaxWidth().padding(all = 10.dp)
 , verticalAlignment = Alignment.CenterVertically
 ){

     Spacer(modifier = Modifier.size(80.dp).clip(CircleShape).background(brush))

     Spacer(modifier = Modifier.width(10.dp))


     Column( verticalArrangement = Arrangement.Center){
         Spacer(
             modifier = Modifier.height(20.dp).fillMaxWidth(fraction = 0.7f).background(brush)

                 .clip(RoundedCornerShape(10.dp))

         )
         Spacer(modifier = Modifier.padding(5.dp))
         Spacer(
             modifier = Modifier.height(20.dp).fillMaxWidth(fraction = 0.9f).background(brush)
                 .clip(RoundedCornerShape(10.dp))

         )
     }

 }
}






//@Composable
//fun ShimmerCard(){
//
//    Card(modifier = Modifier.padding(5.dp).width(150.dp).height(150.dp)
//
//        ,
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White,),
//
//        ){
//
//        Column(modifier = Modifier.fillMaxSize()){
//            Box(
//                modifier = Modifier.size(150.dp).shimmerEffect(),
//
//                ){}
//
//        }
//
//    }
//
//}










