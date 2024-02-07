package com.example.carappcompose.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController){
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .background(primaryColor),
        ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/3ae5a037-9bbf-4c01-88f5-5b0800a40d67/h2uAKVCBce.lottie"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever) }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Login", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(255,165,0))

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Welcome to CarStore", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = Color(168,175,185))
            Spacer(modifier = Modifier.height(10.dp))


            var text by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                value = text,
                leadingIcon = {Icon(imageVector =Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                onValueChange = { text = it },
                label = { Text("Username",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                      focusedBorderColor = primaryColor,
                )
                )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                onValueChange = { password = it },
                label = { Text("Password",   color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
                shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,

            ),
            )


            Spacer(modifier = Modifier.height(20.dp))
            Text(text="Forgot Password?",fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium,   color = Color(168,175,185))
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = {navController.navigate("Main")},
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(255,165,0))
                ){
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Login",
                    fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            }
            Spacer(modifier = Modifier.height(30.dp))
            Row{
                Text(text="Don't have an account?", fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium,   color = Color(168,175,185))



//
//                Text(text="  Sign Up", fontFamily = FontFamily.SansSerif, fontSize = 15.sp,
//                    color = Color(255,165,0),
//
//                    )



            }
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .height(50.dp)
                    .width(150.dp)
                ,
                onClick = { navController.navigate("SignUp")},
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(255,165,0))
            ){
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Sign Up",
                    fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium)

            }
        }





    }
}
//@Preview
//@Composable
//fun SingInPreview(){
//    SignInScreen()
//}
