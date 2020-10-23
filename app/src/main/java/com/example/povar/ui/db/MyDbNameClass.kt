package com.example.povar.ui.db

import android.provider.BaseColumns

object MyDbNameClass:BaseColumns {
    const val TABLE_NAME ="recepts"
    const val COLUMN_NAME_NAME="name"
    const val COLUMN_NAME_INGRIDIENTS="ingridients"
    const val COLUMN_NAME_FORMULA="formula"

    const val DATABASE_VERSION=1
    const val DATABASE_NAME="Povar.db"

    const val CREAT_TABLE ="CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_NAME TEXT,$COLUMN_NAME_INGRIDIENTS TEXT,$COLUMN_NAME_FORMULA TEXT)"

    const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS $TABLE_NAME"
}