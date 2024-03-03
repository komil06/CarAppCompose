package com.example.carappcompose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.carappcompose.Database.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun firebaseUI(context: Context) {

    // on below line creating
    // variable for message.
    val message = remember {
        mutableStateOf("")
    }
    // on below line creating variable for firebase
    // database and database reference.
    val firebaseDatabase = FirebaseDatabase.getInstance();

    val userimage  = firebaseDatabase.reference.child("users")

    val user = userimage.child(UserData.getUserSaved(context))
    val image = user.child("imageUrl")
//    val databaseReference = firebaseDatabase.getReference("Data");

    image.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            val value = snapshot.getValue(String::class.java)

            // after getting the value we are setting
            // our value to message.
            if (value != null) {
                message.value = value
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // calling on cancelled method when we receive
            // any error or we are not able to get the data.
            Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show()
        }
    })

    // on below line creating a column
    // to display our retrieved image view.
    Column(
        // adding modifier for our column


        // on below line adding vertical and
        // horizontal alignment for column.
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line we are creating an image
        Image(
            // on below line we are adding the image url
            // from which we will be loading our image.
            painter = rememberAsyncImagePainter(message.value),

            // on below line we are adding content
            // description for our image.
            contentDescription = "gfg image",

            // on below line we are adding modifier for our
            // image as wrap content for height and width.
            modifier = Modifier
//                .wrapContentSize()
//                .wrapContentHeight()
//                .wrapContentWidth()
                    .clip(CircleShape)
                .size(120.dp)
            ,

            contentScale = ContentScale.Crop,

        )

    }
}