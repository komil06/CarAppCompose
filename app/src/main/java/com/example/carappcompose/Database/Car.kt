package com.example.carappcompose.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(var modelName:String,var price:Int):Parcelable
