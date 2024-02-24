package com.example.carappcompose.Database

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.app.ComponentActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream

class UserData {
    companion object {
        private val users = FirebaseDatabase.getInstance().reference.child("users")


        fun UserCreate(user: UserClass) {
            user.username?.let { username ->
                users.child(username).setValue(user)
            }
        }





        fun getUserSaved(context: Context): String {
            val preferences = context.getSharedPreferences("db", Context.MODE_PRIVATE)
            return preferences.getString("user", "") ?: ""
        }

        fun UserSave(context: Context, user: String) {
            val preferences = context.getSharedPreferences("db", Context.MODE_PRIVATE)
            preferences.edit().putString("user", user).apply()
        }

        fun Usercheck(user: String, callback: (Boolean) -> Unit) {
            users.child(user).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    callback(!dataSnapshot.exists())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(false)
                }
            })
        }
        fun UsersGet(callback: (List<String>) -> Unit) {
            users.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val users = dataSnapshot.children.mapNotNull { it.key }
                    callback(users)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(emptyList())
                }
            })
        }
        fun UserGet(user: TextFieldValue, password: TextFieldValue, callback: (String) -> Unit) {
            val userText = user.text
            val passwordText = password.text

            users.child(userText).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val retrievedUser = dataSnapshot.getValue(UserClass::class.java)
                    val isValidUser = retrievedUser?.password == passwordText

                    if (dataSnapshot.exists() && isValidUser) {
                        callback("Successful Login")
                    } else {
                        callback("Incorrect Username or Password")
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    callback("Error")
                }
            })
        }


        fun FavouritesCreate(user: String, name:String) {

            users.child(user).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val retrievedUser = dataSnapshot.getValue(UserClass::class.java)

                    if (retrievedUser != null) {
                        retrievedUser.favourites = retrievedUser.favourites.plus(name)
                    }
                    users.child(user).setValue(retrievedUser)
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }

        fun isFavourite(user: String, model:String, callback: (Boolean) -> Unit) {

            users.child(user).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val retrievedUser = dataSnapshot.getValue(UserClass::class.java)
                    val favourites = retrievedUser!!.favourites

                    if (dataSnapshot.exists()) {
                        for (item in favourites) {
                            if (item == model) {



                                callback(true)
                            }
                        }
                        callback(false)
                    } else {
                        callback(false)
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    callback(false)
                }
            })
        }


        fun ChangePassword(user: String, password: String) {
            users.child(user).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val changedUser = dataSnapshot.getValue(UserClass::class.java)
                    changedUser?.let {
                        it.password = password
                        users.child(user).setValue(it)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle onCancelled if needed
                }
            })
        }


        fun uploadImageToFirebase(bitmap: Bitmap, context: ComponentActivity, callback :(Boolean, String) -> Unit){


            val storageRef = Firebase.storage.reference
            val imageRef = storageRef.child("images/${bitmap}")

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageData = baos.toByteArray()



            imageRef.putBytes(imageData).addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { uri->
                    val imageUrl = uri.toString()
                    callback(true, imageUrl)
                }.addOnFailureListener{
                    callback(false, null.toString())
                }
            }.addOnFailureListener{
                callback(false, null.toString())
            }
        }




    }
}
