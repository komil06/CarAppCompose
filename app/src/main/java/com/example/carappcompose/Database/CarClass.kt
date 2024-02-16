package com.example.carappcompose.Database

data class CarClass(
    val title: String?,
    var year: String?,
    var brand: String?,
    var model:String?,
    val price:String?,
    val description:String?,
    ) {
    constructor() : this(null, null, null, null, null,null)
}