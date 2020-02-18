package com.pv.demoapp.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.pv.demoapp.data.model.User

class AppPrefHelper constructor(
        val context: Context,
        prefsName: String,
        val gson: Gson
) : PrefHelper {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    companion object {
        const val KEY_USER = "KEY_USER"
        const val KEY_FIRST = "KEY_FIRST"
    }

    override fun saveUser(user: User) {
        val u = gson.toJson(user)
        sharedPreferences.edit().putString(KEY_USER, u).apply()
    }

    override fun getUser(): User? {
        val user = sharedPreferences.getString(KEY_USER, "")
        return try {
            if (user.isNullOrEmpty()) null else {
                gson.fromJson(user, User::class.java)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun clearUser() = sharedPreferences.edit().remove(KEY_USER).apply()

    override fun getFirstTimeOpenAppp(): Boolean = sharedPreferences.getBoolean(KEY_FIRST, true)

    override fun setFirstTimeOpenApp(isFirst: Boolean) = sharedPreferences.edit().putBoolean(KEY_FIRST, isFirst).apply()
}