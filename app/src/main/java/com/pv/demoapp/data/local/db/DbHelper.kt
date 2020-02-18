package com.pv.demoapp.data.local.db

import com.pv.demoapp.data.model.User
import io.reactivex.Observable

interface DbHelper {

    fun insertUser(user: User): Observable<Long>

    fun getAllUser(): Observable<List<User>>

    fun updateUser(user: User): Observable<Boolean>

    fun deleteUserById(id: String): Observable<Boolean>
}