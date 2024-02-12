package com.example.carappcompose.fragments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.ui.theme.poppinsFamily

@Composable
fun FilterScreen(navController: NavController){
  Spacer(modifier = Modifier.height(40.dp))
  LazyRow{
    items(1){
      Spacer(modifier = Modifier.width(20.dp))
      Text(text = "All", fontFamily = poppinsFamily, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
      Spacer(modifier = Modifier.width(20.dp))
      Text(text = "New",fontFamily = poppinsFamily, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
      Spacer(modifier = Modifier.width(20.dp))
      Text(text = "Used",fontFamily = poppinsFamily, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
    }
  }

  }


