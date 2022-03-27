package com.example.internshipassignment

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var pref: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object{
        const val USER_TOKEN = "token";
    }

    //saving the token in the device
    fun saveAutnToken(tok: String){
        val editor = pref.edit()
        editor.putString(USER_TOKEN, tok)
        editor.apply()
    }
    //fetching the auth token
    fun fetchAuthToken():String?{
        return pref.getString(USER_TOKEN, null)
    }
}