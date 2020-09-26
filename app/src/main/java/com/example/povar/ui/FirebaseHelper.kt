package com.example.povar.ui
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var REF_DABATABSE_ROOT: DatabaseReference

const val NODE_RECEPTS = "recepts"
const val  CHIELD_RECEPT_INGRIDIENTS ="ingridients"
const val  CHIELD_RECEPT_ID ="id"
const val  CHIELD_RECEPT_NAME ="name"
const val  CHIELD_RECEPT_FORMULA="formula"
const val  CHIELD_PHOTO_RECEPT_SRC="src"
const val  NODE_USERS = "users"
const val  CHIELD_USER_ID ="id"
const val  CHIELD_USER_PASSWORD ="password"
const val  CHIELD_USER_LOGIN="login"
const val  CHIELD_USER_NAME="name"
const val  CHIELD_PHOTO_USER_SRC="src"




fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DABATABSE_ROOT = FirebaseDatabase.getInstance().reference
}

