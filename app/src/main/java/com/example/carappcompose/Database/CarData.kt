package com.example.carappcompose.Database

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

class CarData {


    companion object {

        private val cars = FirebaseDatabase.getInstance().reference.child("cars")
        private val car = CarClass()
        private val searchList:MutableList<CarClass> = mutableListOf()


        fun CreateCar(car: CarClass) {
            car.title?.let { title ->
              cars.child(title).setValue(car)
            }
        }
//        fun SearchCar(name: String): CarClass {
//            for (i in cars){
//                if (name == car.title){
//                    searchList.plus(car)
//                }
//            }
//
//            return car
//        }


        fun GetCars(callback: (List<CarClass>) -> Unit) {
            cars.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val cars = mutableListOf<CarClass>()
                    dataSnapshot.children.forEach {
                        val car = it.getValue(CarClass::class.java)
                        Log.d("TAG", car.toString())
                        if (car != null) {
                            cars.add(car)
                        }
                    }
                    callback(cars)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(emptyList())
                }
            })
        }

        fun GetCar(name: String, callback: (CarClass) -> Unit) {
            cars.child(name).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val car = dataSnapshot.getValue(CarClass::class.java)
                    if (car != null) {
                        callback(car)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(CarClass())
                }
            })
        }


        fun uploadImageToFirebase(bitmap: Bitmap, context: ComponentActivity, callback :(Boolean, String) -> Unit){


            val storageRef = Firebase.storage.reference
            val imageRef = storageRef.child("carimages/${bitmap}")

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

//        fun searchCars(query:String):MutableList<CarClass>{
//            var carList = mutableListOf<CarClass>()
//            GetCars {
//                carList
//            }
//
//            for (car1 in carList) {
//                if (car1.title!!.contains(query)){
//                    searchList.add(car1)
//                }
//            }
//            return searchList
//        }


//        val con = false
//        val UsedCars = mutableListOf<CarClass>()
//        val NewCars = mutableListOf<CarClass>()
//        fun UsedCars():List<CarClass>{
//            if (car.condition != con){
//                UsedCars.add(car)
//            }
//            return UsedCars
//        }
//
//        fun NewCars():List<CarClass>{
//            if (car.condition == con){
//                NewCars.add(car)
//            }
//            return NewCars
//        }



    }
}