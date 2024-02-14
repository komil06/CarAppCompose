package com.example.carappcompose.fragments

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.carappcompose.Database.UserClass
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController){

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var fullname by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/3ae5a037-9bbf-4c01-88f5-5b0800a40d67/h2uAKVCBce.lottie"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Register", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(255,165,0))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Welcome to CarStore", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = Color(168,175,185))



            OutlinedTextField(
                value = fullname,
                leadingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = null, modifier = Modifier.padding(8.dp), ) },
                onValueChange = { fullname = it },
                label = { Text("Full Name",   color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = username,
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                onValueChange = { username = it },
                label = { Text("Username",   color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                onValueChange = { password = it },
                label = { Text("Password",  color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = {

                    if (" " in username.text) {
                        Toast.makeText(context, "There is not any spaces left ", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        UserData.Usercheck(username.text) {
                            if (it) {
                                UserData.UserCreate(UserClass(fullname.text, username.text, password.text))
                                UserData.UserSave(context, username.text)
                                navController.navigate("Main")
                            } else {
                                Toast.makeText(
                                    context, "Username already exists. Change username", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                          },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(255,165,0))
            ){
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Register",
                    fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

            }
            Spacer(modifier = Modifier.height(30.dp))
            Row{
                Text(text="Already have an account?", fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium,   color = Color(168,175,185))


            }

            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .height(50.dp)
                    .width(150.dp)
                ,
                onClick = { navController.navigate("SignIn")},

                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(255,165,0))
            ){
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Sign In",
                    fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.Medium)

            }

        }
    }
}

