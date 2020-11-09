package com.example.povar.ui
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.snapshot.Node
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var REF_DABATABSE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT:StorageReference


const val NODE_USERS = "users"
const val NODE_RECEPTS = "recepts"
const val NODE_STARS="stars"
const val FOLDER_PROFILE_IMAGE= "profile_image"
const val FOLDER_IMAGE_RECEPT="image_recept"

const val CHIELD_STARS_NAME_USER="name_user"
const val CHIELD_STARS_NAME_RECEPT="name_recept"
const val CHIELD_STARS_OCENKA="ocenka"


const val  CHIELD_RECEPT_INGRIDIENTS ="ingridients"
const val  CHIELD_RECEPT_ID ="id"
const val  CHIELD_RECEPT_NAME ="name"
const val  CHIELD_RECEPT_FORMULA="formula"
const val  CHIELD_PHOTO_RECEPT_SRC="photoUrl"
const val  CHIELD_RECEPT_USER_ID="user_id"
const val   CHIELD_RECEPT_ANGFORMULA="formula_eng"
const val   CHIELD_RECEPT_ANGINGRIDIENS="ingridients_eng"
const val   CHIELD_RECEPT_ANGNAME="name_eng"
const val   CHIELD_RECEPT_CHEK="chek"
const val   CHIELD_RECEPT_COMPLEXITY="complexity"


const val  CHIELD_USER_ID ="id"
const val  CHIELD_USER_PASSWORD ="password"
const val  CHIELD_USER_LOGIN="login"
const val  CHIELD_USER_NAME="name"
const val  CHIELD_PHOTO_USER_SRC="photoUrl"
const val  CHIELD_COUNTER_RECEPT="counter_recept"
const val  CHIELD_USER_TEMA="tema"
const val CHIELD_USER_LANGUAGE="language"









fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DABATABSE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT=FirebaseStorage.getInstance().reference
}

