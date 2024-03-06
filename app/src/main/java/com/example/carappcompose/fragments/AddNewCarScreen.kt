package com.example.carappcompose.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.Database.UserClass
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.NavigationItem
import com.example.carappcompose.R
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewCarScreen(navController: NavController){


    val context = LocalContext.current

            var title by remember { mutableStateOf(TextFieldValue("")) }
            var brand by remember { mutableStateOf(TextFieldValue("")) }
            var condition by remember { mutableStateOf(TextFieldValue(""))}
            var year by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }


    var imgUrl by remember {mutableStateOf("") }
    val isUploading  = remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
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








    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(2)
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("Main") }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                modifier = Modifier
                                    .height(35.dp)
                                    .width(40.dp)
                            )
                        }

                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Localized description",
                                modifier = Modifier
                                    .height(35.dp)
                                    .width(40.dp)
                            )
                        }
                    },

                    )
            }
        )
            {

            }



        Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            if(isUploading.value){
                CircularProgressIndicator(
                    modifier = Modifier.width(60.dp).height(60.dp),
                    color = primaryColor
                )
            }
        }
            Column(modifier = Modifier.fillMaxSize().padding(top =60.dp)
                .verticalScroll(rememberScrollState())

                , horizontalAlignment = Alignment.CenterHorizontally){

                if(bitmap !=null){
                    Image(
                        bitmap = bitmap?.asImageBitmap()!!,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)

                            .size(130.dp)


                            .clickable { showDialog = true }
                    )
                }

                else{

                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)

                            .size(130.dp)



                            .clickable { showDialog = true }
                    )
                }

            OutlinedTextField(

                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp,top = 10.dp),
                value = title,
                leadingIcon = {Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                onValueChange = { title = it },
                label = { Text("Car Name",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primaryColor,
                )
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp,top = 10.dp),
                value = year,
                leadingIcon = {Icon(imageVector =Icons.Default.Edit, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                onValueChange = { year = it },
                label = { Text("Manufactured year",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primaryColor,
                )
            )


                    OutlinedTextField(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp).fillMaxWidth(),
                        value = brand,
                        leadingIcon = {Icon(imageVector =Icons.Default.Star, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                        onValueChange = { brand = it },
                        label = { Text("Brand",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = primaryColor,
                        )
                    )



                Row( modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 10.dp), horizontalArrangement = Arrangement.SpaceAround){
                    OutlinedTextField(
                        modifier = Modifier.weight(0.5f),
                        value = condition,
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_vpn_key_24),
                                contentDescription = "Komilni puli",
//                        modifier = Modifier.width(30.dp).height(20.dp)
                            )
                                      },

                        onValueChange = { condition = it },
                        label = { Text("Condition",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = primaryColor,
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedTextField(
                        modifier = Modifier.weight(0.5f),
                        value = price,
                    leadingIcon = {Image(
                        painter = painterResource(id = R.drawable.baseline_attach_money_24),
                        contentDescription = "Komilni puli",
//                        modifier = Modifier.width(30.dp).height(20.dp)
                    )},
                        onValueChange = { price = it },
                        label = { Text("Price",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = primaryColor,
                        )
                    )

                }


                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp,top = 10.dp).height(100.dp),
                    value = description,
//                    leadingIcon =
//                    {Icon(imageVector =Icons.Default.Info, contentDescription = null, modifier = Modifier.padding(8.dp)) },
                    onValueChange = { description = it },
                    label = { Text("Additional description",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = primaryColor,
                    )
                )

    Button(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        onClick = {

            isUploading.value = true

            bitmap.let{bitmap ->
                if(bitmap != null){
                    CarData.uploadImageToFirebase(bitmap, context as ComponentActivity) { success, imageUrl ->
                        isUploading.value = false

                        if(success){
                            imageUrl.let{
                                imgUrl = it
                            }
                            CarData.CreateCar(CarClass(UserData.getUserSaved(context),title.text, year.text, brand.text, price.text, description.text, imgUrl))

                            Toast.makeText(
                                context, "Mashinangiz ro'yxatga muvaqqiyatli qo'shildi", Toast.LENGTH_SHORT
                            ).show()

                            navController.navigate("Profile")
                        }
                        else{
                            Toast.makeText(context, "Not Save", Toast.LENGTH_SHORT).show()

                        }

                    }
                }
            }






        },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(255,165,0))
    ){
        Text(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            text = "Qo'shish",
            fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

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
                        .width(250.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(255,165,0))



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

                            fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
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
                            fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,

                            color = Color.White
                        )
                    }

                    Column(
                        modifier = Modifier.padding(bottom = 60.dp)
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

}}