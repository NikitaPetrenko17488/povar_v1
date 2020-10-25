package com.example.povar.models

data class User(
    val id:String ="",
    var name:String ="",
    var login:String="",
    var password:String="",
    var photoUrl:String="",
    var admin:Int=0,
    var translater:Int=0,
    var counter_recept:Int=0,
    var tema:Boolean=false,
    var language:String=""
)