package com.example.carappcompose.fragments

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController){
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/3ae5a037-9bbf-4c01-88f5-5b0800a40d67/h2uAKVCBce.lottie"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever) }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Login")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Welcome to CarStore")
            var text by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                value = text,
                leadingIcon = {Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                onValueChange = { text = it },
                label = { Text("Username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                        shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp)




            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text="Forgot Password?")
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier.padding(top = 27.dp),onClick = {}){
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Login",
                    fontSize = 19.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row{
                Text(text="Don't have an acoount?")

                Text(text="  Sign Up")
            }

        }





    }
}
//@Preview
//@Composable
//fun SingInPreview(){
//    SignInScreen()
//}
