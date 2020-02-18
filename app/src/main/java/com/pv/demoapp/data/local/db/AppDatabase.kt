package com.pv.demoapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pv.demoapp.data.local.db.dao.UserDao
import com.pv.demoapp.data.model.City
import com.pv.demoapp.data.model.District
import com.pv.demoapp.data.model.User


@Database(entities = [
    User::class,
    City::class,
    District::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}