package com.example.carappcompose.fragments

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.carappcompose.Database.UserClass
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.R
import com.example.carappcompose.ui.theme.poppinsFamily
import com.google.firebase.Firebase
import com.google.firebase.database.database

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController){

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var fullname by remember { mutableStateOf(TextFieldValue("")) }
//    var password by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    var imgUrl by remember {mutableStateOf("") }
    val database  = Firebase.database
    val isUploading  = remember { mutableStateOf(false) }
    val emty by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null)}
    var showDialog by remember { mutableStateOf(false)}


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){uri: Uri? ->
        uri?.let{
            bitmap = if(Build.VERSION.SDK_INT <28){
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }
            else{
                var source = ImageDecoder.createSource(context.contentResolver,it)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }


    val cLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.TakePicturePreview()

    ){

        bitmap = it
    }












    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .verticalScroll(rememberScrollState())

    ) {
//        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/3ae5a037-9bbf-4c01-88f5-5b0800a40d67/h2uAKVCBce.lottie"))
//            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
//        }
        Column(modifier = Modifier
            .fillMaxSize()
            ,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Register", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(255,165,0))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Welcome to CarStore", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = Color(168,175,185))

            Spacer(modifier = Modifier.height(10.dp))



            if(bitmap !=null){
                Image(
                    bitmap = bitmap?.asImageBitmap()!!,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .size(100.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Green,
                            shape = CircleShape
                        )

                        .clickable { showDialog = true }
                )
            }

            else{

                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .size(130.dp)



                        .clickable { showDialog = true }
                )
            }




            OutlinedTextField(
                value = fullname,
                leadingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = null, modifier = Modifier.padding(8.dp), ) },
                onValueChange = { fullname = it },
                label = { Text("Full Name",   color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                shape = RoundedCornerShape(12.dp),
//                shadow = Shadow(
//                    color = Color.Red,
//                    offset = Offset(2.0f, 5.0f),
//                    blurRadius = 2f
//                )
            )

            Spacer(modifier = Modifier.height(15.dp))


            OutlinedTextField(
                value = username,
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                onValueChange = { username = it },
                label = { Text("Username",   color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
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
                trailingIcon = {
                    if(password.isNotEmpty()){



                        val visibilityIcon = if(passwordVisibility){
                            painterResource(id = R.drawable.baseline_visibility_24)
                        }

                        else{
                            painterResource(id = R.drawable.baseline_visibility_off_24)

                        }


                        Icon(
                            painter = visibilityIcon,
                            contentDescription = null,
                            Modifier.clickable {
                                passwordVisibility = !passwordVisibility
                            }
                        )
                    }
                },



            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                onClick = {
                    isUploading.value = true

                    if (" " in username.text) {
                        Toast.makeText(context, "There is not any spaces left ", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        UserData.Usercheck(username.text) {
                            if (it) {


                                bitmap.let{bitmap ->
                                    if(bitmap != null){
                                        UserData.uploadImageToFirebase(bitmap, context as ComponentActivity) { success, imageUrl ->
                                            isUploading.value = false

                                            if(success){
                                                imageUrl.let{
                                                    imgUrl = it
                                                }
                                                        UserData.UserCreate(UserClass(fullname.text, username.text, password, emptyList(), imgUrl))
                                                UserData.UserSave(context, username.text)
                                                Toast.makeText(context, "User saved", Toast.LENGTH_SHORT).show()

                                                navController.navigate("Main")
                                            }
                                            else{
                                                Toast.makeText(context, "Not Save", Toast.LENGTH_SHORT).show()

                                            }

                                        }
                                    }
                                }




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


            Spacer(modifier = Modifier.height(10.dp))

            if(isUploading.value){
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

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


        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(

                )
                .padding(bottom = 10.dp)

        ) {


            if(showDialog){
                Row(
                    verticalAlignment =Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.LightGray)
                ){

                    Column (modifier = Modifier.padding(start = 10.dp)){


                        Image(
                            painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    cLauncher.launch()
                                    showDialog = false
                                }
                        )

                        Text(
                            text = "Camera",
                            color = Color.White
                        )


                    }



                    Spacer(modifier = Modifier.padding(30.dp))



                    Column(){


                        Image(
                            painter = painterResource(id = R.drawable.baseline_image_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    launcher.launch("image/*")
                                    showDialog = false
                                }
                        )

                        Text(
                            text = "Gallery",
                            color = Color.White
                        )
                    }

                    Column(
                        modifier = Modifier.padding(start = 20.dp,bottom = 60.dp, end = 0.dp)
                    ){




                        Text(

                            text = "X",
                            fontFamily = poppinsFamily, fontWeight = FontWeight.Normal,
                            color = Color.White,
                            modifier = Modifier
                                .clickable { showDialog = false }
                        )
                    }

                }
            }
        }

    }


//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Bottom,
//        modifier = Modifier
//            .fillMaxSize()
//    ){
//
//        if(isUploading.value){
//            CircularProgressIndicator(
//                modifier = Modifier.padding(bottom = 50.dp),
//                color = Color.Black
//            )
//        }
//
//    }
}

