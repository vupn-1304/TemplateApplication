package com.pv.demoapp.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.pv.demoapp.data.AppDataManager
import com.pv.demoapp.data.DataManager
import com.pv.demoapp.data.local.db.AppDatabase
import com.pv.demoapp.data.local.db.AppDbHelper
import com.pv.demoapp.data.local.db.DbHelper
import com.pv.demoapp.data.local.prefs.AppPrefHelper
import com.pv.demoapp.data.local.prefs.PrefHelper
import com.pv.demoapp.data.remote.ApiHelper
import com.pv.demoapp.data.remote.AppApiHelper
import com.utils.SchedulerProvider
import org.koin.core.module.Module
import org.koin.dsl.module


//define app module gson, data manager, etc...
val appModule: Module = module {

    single { SchedulerProvider() }

    single { AppPrefHelper(get(), "prefsapp", get()) as PrefHelper }

    single { AppDataManager(get(), get(), get()) as DataManager }

    single { AppDbHelper(get()) as DbHelper }

    single { AppApiHelper() as ApiHelper }

    single {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'X'").create()!!
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "databaseapp.sqlite").build()
    }
}
val demoModule = listOf(appModule, viewModule)
