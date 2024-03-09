package com.example.carappcompose.Database

data class CarClass(
    var user:String?,
    val title: String?, //+
    var year: String?, //+
    val price:String?, //+
    val mileage:String?, //+
    val condition:String?, //+
    val color:String?, //-
    val description:String?, //+
    var imageUrl:String //+

    ) {
    constructor() : this(null,null, null,null, null, null,null,null, null.toString())
}