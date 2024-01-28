package com.example.whiteboardapp

import android.content.Context
import android.content.SharedPreferences

class Sharepref(context:Context) {
    private val userfile:String="whiteborad"

    private val datashared: SharedPreferences =context.getSharedPreferences(userfile,Context.MODE_PRIVATE)
    private val editshared: SharedPreferences.Editor=datashared.edit()

    fun setdata(key:String,value:String){
        editshared.putString(key,value)
        editshared.apply()
    }

    fun getdata(key:String): String {
        return datashared.getString(key,"").toString()
    }
}