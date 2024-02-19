package com.example.carappcompose.Database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CarData {
    companion object {

        private val cars = FirebaseDatabase.getInstance().reference.child("cars")



        fun CreateCar(car: CarClass) {
            car.title?.let { title ->
              cars.child(title).setValue(car)
            }
        }


        fun GetCars(callback: (List<String>) -> Unit) {
           cars.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val cars = dataSnapshot.children.mapNotNull { it.key }

                    callback(cars)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    callback(emptyList())
                }
            })
        }

        fun WishlistedCars(){

        }



    }
}