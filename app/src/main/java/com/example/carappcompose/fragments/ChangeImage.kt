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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
import com.example.carappcompose.Database.UserClass
import com.example.carappcompose.Database.UserData
import com.example.carappcompose.NavigationItem
import com.example.carappcompose.R
import com.example.carappcompose.firebaseUI
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor
import kotlinx.coroutines.launch

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ChangeImage(navController:NavController){
//    val context = LocalContext.current
//    var imgUrl by remember {mutableStateOf("") }
//    val isUploading  = remember { mutableStateOf(false) }
//
//    var bitmap by remember { mutableStateOf<Bitmap?>(null)}
//    var showDialog by remember { mutableStateOf(false)}
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ){uri: Uri? ->
//        uri?.let{
//            bitmap = if(Build.VERSION.SDK_INT <28){
//                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
//            }
//            else{
//                var source = ImageDecoder.createSource(context.contentResolver,it)
//                ImageDecoder.decodeBitmap(source)
//            }
//        }
//    }
//
//
//    val cLauncher = rememberLauncherForActivityResult (
//        contract = ActivityResultContracts.TakePicturePreview()
//
//    ){
//
//        bitmap = it
//    }
//
//    val items = listOf(
//        NavigationItem(
//            title = "Main",
//            selectedIcon = Icons.Filled.Home,
//            unselectedIcon = Icons.Outlined.Home,
//
//
//
//            ),
//        NavigationItem(
//            title = "WishList",
//            selectedIcon = Icons.Filled.Favorite,
//            unselectedIcon = Icons.Outlined.FavoriteBorder,
//
//            ),
//        NavigationItem(
//            title = "Profile",
//            selectedIcon = Icons.Filled.Person,
//            unselectedIcon = Icons.Outlined.Person,
//        ),
//
//        )
//
//
//
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//        val scope = rememberCoroutineScope()
//        var selectedItemIndex by rememberSaveable {
//            mutableStateOf(2)
//        }
//
////        var images = UserData.getSavedImage(context,)
//        ModalNavigationDrawer(
//
//            drawerContent = {
//                ModalDrawerSheet {
//
//                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
//
//                        Column(modifier = Modifier.fillMaxWidth().padding(top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally,
//
//                            ) {
//
//                            firebaseUI(LocalContext.current)
//
//                        }
//
//
//                        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = "Welcome to CarStore", fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = Color(168,175,185))
//                        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text = UserData.getUserSaved(context), fontFamily = poppinsFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold,   color = primaryColor)
//
//                    }
//                    Spacer(modifier = Modifier.height(50.dp))
//
//
//                    items.forEachIndexed { index, item ->
//                        NavigationDrawerItem(
//                            label = {
//                                Text(text = item.title,
//                                    fontFamily = poppinsFamily,
//                                    fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
//                                )
//                            },
//                            selected = index == selectedItemIndex,
//                            onClick = {
//
//                                selectedItemIndex = index
//                                navController.navigate("${item.title}")
//                                scope.launch {
//                                    drawerState.close()
//                                }
//                            },
//                            icon = {
//                                Icon(
//                                    imageVector = if (index == selectedItemIndex) {
//                                        item.selectedIcon
//                                    } else item.unselectedIcon,
//                                    contentDescription = item.title
//                                )
//                            },
////                            badge = {
////                                item.badgeCount?.let {
////                                    Text(text = item.badgeCount.toString())
////                                }
////                            },
//                            modifier = Modifier
//                                .padding(NavigationDrawerItemDefaults.ItemPadding)
//                                .clickable {
//                                }
//                            ,
//
//
//                            )
//                    }
//
//
//
//
//                    Button(modifier = Modifier
//                        .padding(top = 5.dp)
//                        .fillMaxWidth(),
//                        colors = ButtonDefaults.buttonColors(Color.Transparent),
//                        onClick = {
//                            UserData.UserSave(context, "",)
//                            navController.navigate("SignIn")
//                        }) {
//                        Text(text = "Log out", fontSize = 20.sp,
//                            fontFamily = poppinsFamily,
//                            fontWeight = FontWeight.SemiBold,
//                            color = Color.Black
//                        )
//                    }
//                }
//            },
//            drawerState = drawerState
//        ) {
//
//
//            Scaffold(
//
//                topBar = {
//                    CenterAlignedTopAppBar(
//                        title = {
//                            Text(
//                                "CarStore",
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis,
//                                fontFamily = poppinsFamily,
//                                fontWeight = FontWeight.Bold,
//                                fontSize = 24.sp,
//                                color = primaryColor
//                            )
//                        },
//                        navigationIcon = {
//                            IconButton(onClick = {
//                                scope.launch {
//                                    drawerState.open()
//                                }
//                            }) {
//                                Icon(
//                                    imageVector = Icons.Default.Menu,
//                                    contentDescription = "Menu",
//                                    modifier = Modifier
//                                        .height(35.dp)
//                                        .width(40.dp)
//                                )
//                            }
//                        },
//                        actions = {
//                            IconButton(onClick = { /* do something */ }) {
//                                Icon(
//                                    imageVector = Icons.Filled.Notifications,
//                                    contentDescription = "Localized description",
//                                    modifier = Modifier
//                                        .height(35.dp)
//                                        .width(40.dp)
//                                )
//                            }
//                        },
//                    )
//                },
//                bottomBar = {
//
//                    NavigationBar(
//                        modifier = Modifier.zIndex(3f).padding(
//                            bottom = 20.dp,
//                            start = 25.dp, end = 25.dp, top = 20.dp
//                        )
//                            .clip(RoundedCornerShape(25.dp))
////                        .background(Color.White)
//                            .border(
//                                BorderStroke(1.dp, Color.LightGray),
//                                shape = RoundedCornerShape(25.dp),
//                            ),
//
//
//                        ) {
//                        items.forEachIndexed { index, item ->
//                            NavigationBarItem(
//                                selected = selectedItemIndex == index,
//                                onClick = {
//                                    selectedItemIndex = index
//                                    navController.navigate("${item.title}")
//
//                                },
//                                label = {
//                                    Text(
//                                        text = item.title,
//                                        fontFamily = poppinsFamily,
//                                        fontWeight = FontWeight.SemiBold,
//                                        modifier = Modifier.padding(top = 20.dp)
//                                    )
//                                },
//                                alwaysShowLabel = false,
//                                icon = {
//                                    BadgedBox(
//                                        badge = {
//                                            if (item.badgeCount != null) {
//                                                Badge {
//                                                    Text(text = item.badgeCount.toString())
//                                                }
//                                            }
//                                        }
//                                    ) {
//                                        Icon(
//                                            imageVector = if (index == selectedItemIndex) {
//                                                item.selectedIcon
//                                            } else item.unselectedIcon,
//                                            contentDescription = item.title,
//
//                                            )
//                                    }
//                                }
//                            )
//                        }
//                    }
//                },
//
//                )
//            {
//
//            }
//
//
//
//
//
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 70.dp, start = 10.dp,end =10.dp)
//            ) {
//
//                if(bitmap !=null){
//                    Image(
//                        bitmap = bitmap?.asImageBitmap()!!,
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .background(Color.LightGray)
//                            .size(100.dp)
//                            .border(
//                                width = 1.dp,
//                                color = Color.Green,
//                                shape = CircleShape
//                            )
//
//                            .clickable { showDialog = true }
//                    )
//                }
//
//                else{
//
//                    Image(
//                        painter = painterResource(id = R.drawable.baseline_person_24),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .background(Color.LightGray)
//                            .size(130.dp)
//
//
//
//                            .clickable { showDialog = true }
//                    )
//                }
//
//
//                Button(modifier = Modifier.padding(top = 10.dp, start = 115.dp), onClick = {
//
//
//                    bitmap.let{bitmap ->
//                        if(bitmap != null){
//                            UserData.uploadImageToFirebase(bitmap, context as ComponentActivity) { success, imageUrl ->
//                                isUploading.value = false
//
//                                if(success){
//                                    imageUrl.let{
//                                        imgUrl = it
//                                    }
//
//                                    UserData.changeImage(UserData.getUserSaved(context), imgUrl)
//                                    Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
//
//                                    navController.navigate("Main")
//                                }
//                                else{
//                                    Toast.makeText(context, "Not Save", Toast.LENGTH_SHORT).show()
//
//                                }
//
//                            }
//                        }
//                    }
//
//
//                }) {
//                    Text(text = "Change", fontSize = 20.sp)
//
//                }
//
//                Column(modifier = Modifier.fillMaxWidth().padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//
//                    if(isUploading.value){
//                        CircularProgressIndicator(
//                            modifier = Modifier.width(60.dp).height(60.dp),
//                            color = primaryColor
//                        )
//                    }
//                }
//
//
//            }
//            Column(
//                verticalArrangement = Arrangement.Bottom,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .fillMaxSize(
//
//                    )
//                    .padding(bottom = 100.dp)
//
//            ) {
//
//
//                if(showDialog){
//                    Row(
//                        verticalAlignment =Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center,
//                        modifier = Modifier
//                            .width(300.dp)
//                            .height(100.dp)
//                            .clip(RoundedCornerShape(30.dp))
//                            .background(Color.LightGray)
//                    ){
//
//                        Column (modifier = Modifier.padding(start = 10.dp)){
//
//
//                            Image(
//                                painter = painterResource(id = R.drawable.baseline_photo_camera_24),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(50.dp)
//                                    .clickable {
//                                        cLauncher.launch()
//                                        showDialog = false
//                                    }
//                            )
//
//                            Text(
//                                text = "Camera",
//                                color = Color.White
//                            )
//
//
//                        }
//
//
//
//                        Spacer(modifier = Modifier.padding(30.dp))
//
//
//
//                        Column(){
//
//
//                            Image(
//                                painter = painterResource(id = R.drawable.baseline_image_24),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .size(50.dp)
//                                    .clickable {
//                                        launcher.launch("image/*")
//                                        showDialog = false
//                                    }
//                            )
//
//                            Text(
//                                text = "Gallery",
//                                color = Color.White
//                            )
//                        }
//
//                        Column(
//                            modifier = Modifier.padding(start = 20.dp,bottom = 60.dp, end = 0.dp)
//                        ){
//
//
//
//
//                            Text(
//
//                                text = "X",
//                                fontFamily = poppinsFamily, fontWeight = FontWeight.Normal,
//                                color = Color.White,
//                                modifier = Modifier
//                                    .clickable { showDialog = false }
//                            )
//                        }
//
//                    }
//                }
//            }
//
//        }
//    }
//}