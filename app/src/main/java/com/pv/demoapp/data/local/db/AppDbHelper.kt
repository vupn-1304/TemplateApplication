package com.pv.demoapp.data.local.db

import com.pv.demoapp.data.model.User
import io.reactivex.Observable

class AppDbHelper constructor(
        private val appDatabase: AppDatabase
) : DbHelper {
    override fun insertUser(user: User): Observable<Long> =
            Observable.fromCallable { appDatabase.userDao().insertUser(user) }

    override fun getAllUser(): Observable<List<User>> =
            Observable.fromCallable { appDatabase.userDao().getAllUser() }

    override fun updateUser(user: User): Observable<Boolean> =
            Observable.fromCallable {
                appDatabase.userDao().updateUser(user)
                true
            }

    override fun deleteUserById(id: String): Observable<Boolean> =
            Observable.fromCallable {
                appDatabase.userDao().deleteAllUser()
                true
            }
}