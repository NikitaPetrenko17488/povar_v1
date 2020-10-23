package com.example.povar.ui.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.fragment.app.FragmentActivity
import com.example.povar.models.Recept

class MyDbManager(context: FragmentActivity?) {
    val myDbHelper=MyDbHelper(context!!)
    var db:SQLiteDatabase?=null

    fun openDb(){
        db=myDbHelper.writableDatabase
    }
    fun insertToDb(name:String, ingridients:String, formula:String)
    {
        val Values=ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_INGRIDIENTS, ingridients)
            put(MyDbNameClass.COLUMN_NAME_FORMULA, formula)
        }
        db?.insert(MyDbNameClass.TABLE_NAME,null, Values)
    }
    fun ReadDb():ArrayList<Recept>{
        val dataList= arrayListOf<Recept>()
        val cursor =db?.query(MyDbNameClass.TABLE_NAME,null,null,null,
            null,null,null)


           while (cursor?.moveToNext()!!){
            var dataName=cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_NAME))
               var dataIngridients=cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_INGRIDIENTS))
               var dataFormula=cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_FORMULA))


               dataList.add(Recept("",dataName,dataIngridients,dataFormula,"",""))
               dataName=""
               dataIngridients=""
               dataFormula=""
            }

        cursor.close()
        return dataList

    }
    fun closeDb(){
        myDbHelper.close()
    }

    fun deleteDb(){

        val deletedRows = db?.delete(MyDbNameClass.TABLE_NAME, null,null)
    }

}
