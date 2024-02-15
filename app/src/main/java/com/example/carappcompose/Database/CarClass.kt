package com.example.carappcompose.Database

data class CarClass(
    val title: String?,
    var year: String?,
    var brand: String?,
) {
    constructor() : this(null, null, null)
}