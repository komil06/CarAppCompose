package com.example.carappcompose.Database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CarData {
    companion object {

        private val cars = FirebaseDatabase.getInstance().reference.child("cars")
        private val car = CarClass()
        private val searchList:List<CarClass> = emptyList()


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

        fun WishlistedCars(){

        }


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