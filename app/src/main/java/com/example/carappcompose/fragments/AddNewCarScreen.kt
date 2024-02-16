package com.example.carappcompose.fragments

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.Database.CarClass
import com.example.carappcompose.Database.CarData
import com.example.carappcompose.ui.theme.poppinsFamily
import com.example.carappcompose.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewCarScreen(navController: NavController){
    val context = LocalContext.current

            var title by remember { mutableStateOf(TextFieldValue("")) }
            var brand by remember { mutableStateOf(TextFieldValue("")) }
            var year by remember { mutableStateOf(TextFieldValue("")) }
Column(modifier = Modifier.fillMaxWidth().padding(top =100.dp), horizontalAlignment = Alignment.CenterHorizontally){

            OutlinedTextField(
                value = title,
                leadingIcon = {Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                onValueChange = { title = it },
                label = { Text("Title",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primaryColor,
                )
            )


            OutlinedTextField(
                value = year,
                leadingIcon = {Icon(imageVector =Icons.Default.Edit, contentDescription = null, modifier = Modifier.padding(8.dp)) },

                onValueChange = { year = it },
                label = { Text("Enter year",    color = Color(168,175,185), fontFamily = poppinsFamily, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primaryColor,
                )
            )

    OutlinedTextField(
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

    Button(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        onClick = {


            CarData.CreateCar(CarClass(title.text, brand.text, year.text))
//            UserData.UserSave(context, username.text)

            Toast.makeText(
                context, "Mashinangiz ro'yxatga muvaqqiyatli qo'shildi", Toast.LENGTH_SHORT
            ).show()
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

}