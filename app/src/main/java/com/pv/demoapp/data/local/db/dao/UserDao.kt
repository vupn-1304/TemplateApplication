package com.pv.demoapp.data.local.db.dao

import androidx.room.*
import com.pv.demoapp.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(list: List<User>): List<Long>

    @Query("select * from user_info where username like '%' || :query || '%'")
    fun searchUser(query: String): List<User>

    @Query("select * from user_info")
    fun getAllUser(): List<User>

    @Query("select * from user_info where username=:name")
    fun getUserByName(name: String): List<User>

    @Update
    fun updateUser(user: User)

    @Query("delete from user_info")
    fun deleteAllUser()

    @Delete
    fun deleteUser(user: User)

    @Query("delete from user_info where id=:idUser")
    fun deleteUserById(idUser: String)
}