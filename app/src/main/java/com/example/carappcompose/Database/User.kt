package com.example.carappcompose.Database

data class User(val fullName:String?, val phoneNumber:String?, var password:String?){
    constructor():this(null,null,null)
}
