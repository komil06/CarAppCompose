package com.example.carappcompose.Database

data class CarClass(
    var user:String?,
    val title: String?,
    var year: String?,
    var brand: String?,
    val price:String?,
//    val condition:Boolean?=false,
    val description:String?,
    var imageUrl:String

    ) {
    constructor() : this(null,null, null, null, null,null, null.toString())
}