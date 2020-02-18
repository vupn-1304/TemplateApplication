package com.pv.demoapp.data

import com.pv.demoapp.data.local.db.DbHelper
import com.pv.demoapp.data.local.prefs.PrefHelper
import com.pv.demoapp.data.model.User
import com.pv.demoapp.data.remote.ApiHelper
import io.reactivex.Observable

class AppDataManager constructor(
        private val dbHelper: DbHelper,
        private val prefHelper: PrefHelper,
        private val apiHelper: ApiHelper
) : DataManager {

    override fun saveUser(user: User) = prefHelper.saveUser(user)

    override fun getUser(): User? = prefHelper.getUser()

    override fun clearUser() = prefHelper.clearUser()

    override fun getFirstTimeOpenAppp(): Boolean = prefHelper.getFirstTimeOpenAppp()

    override fun setFirstTimeOpenApp(isFirst: Boolean) = prefHelper.setFirstTimeOpenApp(isFirst)

    override fun insertUser(user: User): Observable<Long> = dbHelper.insertUser(user)

    override fun getAllUser(): Observable<List<User>> = dbHelper.getAllUser()

    override fun updateUser(user: User): Observable<Boolean> = dbHelper.updateUser(user)

    override fun deleteUserById(id: String): Observable<Boolean> = dbHelper.deleteUserById(id)

    override fun getCity() = apiHelper.getCity()

    override fun getDistrict() = apiHelper.getDistrict()
}