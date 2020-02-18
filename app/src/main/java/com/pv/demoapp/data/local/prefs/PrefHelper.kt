package com.pv.demoapp.data.local.prefs

import com.pv.demoapp.data.model.User

interface PrefHelper {

    fun saveUser(user: User)

    fun getUser(): User?

    fun clearUser()

    fun getFirstTimeOpenAppp(): Boolean

    fun setFirstTimeOpenApp(isFirst: Boolean)
}