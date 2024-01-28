package com.example.carappcompose.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
//        scale.animateTo(
//            targetValue =1.5f,
//            animationSpec = tween(
//                durationMillis = 4000,
//                easing = {
//
//                    OvershootInterpolator(1f).getInterpolation(it)
//                })
//        )

        delay(3000)

        navController.navigate("SignIn")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center,
    )
    {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/2efb0d40-0e69-4566-b718-400d5a88fcbb/IyXFFjxjPD.lottie"))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}