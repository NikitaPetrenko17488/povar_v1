package com.example.povar.models

data class User(
    val id:String ="",
    var name:String ="",
    var login:String="",
    var password:String="",
    var photoUrl:String="",
    var admin:Int=0
)